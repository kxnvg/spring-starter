package com.niksoft.bookstore.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.niksoft.bookstore.domain.Book;
import com.niksoft.bookstore.repository.BookRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link Book} entity.
 */
public interface BookSearchRepository extends ElasticsearchRepository<Book, Long>, BookSearchRepositoryInternal {}

interface BookSearchRepositoryInternal {
    Page<Book> search(String query, Pageable pageable);

    Page<Book> search(Query query);

    @Async
    void index(Book entity);

    @Async
    void deleteFromIndexById(Long id);
}

class BookSearchRepositoryInternalImpl implements BookSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final BookRepository repository;

    BookSearchRepositoryInternalImpl(ElasticsearchTemplate elasticsearchTemplate, BookRepository repository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Page<Book> search(String query, Pageable pageable) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery.setPageable(pageable));
    }

    @Override
    public Page<Book> search(Query query) {
        SearchHits<Book> searchHits = elasticsearchTemplate.search(query, Book.class);
        List<Book> hits = searchHits.map(SearchHit::getContent).stream().toList();
        return new PageImpl<>(hits, query.getPageable(), searchHits.getTotalHits());
    }

    @Override
    public void index(Book entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), Book.class);
    }
}

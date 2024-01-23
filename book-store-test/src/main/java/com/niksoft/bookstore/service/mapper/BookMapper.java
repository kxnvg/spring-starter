package com.niksoft.bookstore.service.mapper;

import com.niksoft.bookstore.domain.Book;
import com.niksoft.bookstore.service.dto.BookDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Book} and its DTO {@link BookDTO}.
 */
@Mapper(componentModel = "spring")
public interface BookMapper extends EntityMapper<BookDTO, Book> {}

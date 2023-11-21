package org.kxnvg.config;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class DatabaseProperties {

    private String username;
    private String password;
    private String driver;
    private String url;
    private String hosts;
    private PoolProperties pool;
    private List<PoolProperties> pools;
    private Map<String, Object> properties;

    @Data
    @NoArgsConstructor
    public static class PoolProperties {
        private Integer size;
        private Integer timeout;
    }
}

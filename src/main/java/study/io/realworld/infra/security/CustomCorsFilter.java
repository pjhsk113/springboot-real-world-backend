package study.io.realworld.infra.security;


import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

public class CustomCorsFilter extends CorsFilter {

    public CustomCorsFilter() {
        super(configSource());
    }

    private static UrlBasedCorsConfigurationSource configSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.setMaxAge(36000L);
        config.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        config.setAllowedMethods(List.of("GET", "HEAD", "POST", "PUT", "DELETE", "OPTION"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}

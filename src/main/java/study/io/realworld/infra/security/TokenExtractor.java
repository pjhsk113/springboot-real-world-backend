package study.io.realworld.infra.security;

import java.util.Optional;

public class TokenExtractor {
    private static final String HEADER_PERFIX = "Token ";
    private final String header;

    public TokenExtractor(String header) {
        this.header = header;
    }

    public Optional<String> extract() {
        if (header == null || header.isBlank()) {
            return Optional.empty();
        }

        if (header.length() < HEADER_PERFIX.length()) {
            return Optional.empty();
        }

        return Optional.of(header.substring(HEADER_PERFIX.length()));
    }
}

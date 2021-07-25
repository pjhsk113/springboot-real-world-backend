package study.io.realworld.infra.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TokenExtractorTest {

    @MethodSource("invalidTokenExtractor")
    @ParameterizedTest
    void when_invaild_extract_expect_return_empty(TokenExtractor tokenExtractor) {

        Optional<String> result = tokenExtractor.extract();

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void when_valid_extract_expect_return_string() {
        TokenExtractor tokenExtractor = new TokenExtractor("Token header");

        Optional<String> result = tokenExtractor.extract();

        assertThat(result.get()).isEqualTo("header");
    }

    private static Stream<Arguments> invalidTokenExtractor() {
        return Stream.of(
                Arguments.of(new TokenExtractor(null)),
                Arguments.of(new TokenExtractor("")),
                Arguments.of(new TokenExtractor("aaa"))
        );
    }
}
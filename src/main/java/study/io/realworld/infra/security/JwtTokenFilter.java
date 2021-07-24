package study.io.realworld.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import study.io.realworld.backend.domain.user.User;
import study.io.realworld.backend.domain.user.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private static final String AUTH_HEADER = "Authorization";

    private final String signKey;
    private final UserRepository userRepository;

    public JwtTokenFilter(@Value("${token.signKey}") String signKey, UserRepository userRepository) {
        this.signKey = signKey;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        TokenExtractor tokenExtractor = new TokenExtractor(httpServletRequest.getHeader(AUTH_HEADER));
        tokenExtractor.extract().ifPresent(accessToken -> {
            TokenProvider tokenProvider = new TokenProvider(signKey, accessToken);
            String subject = tokenProvider.parseToken().orElseThrow();
            User user = userRepository.findByEmail(subject).orElseThrow(
                    () -> new IllegalArgumentException("user not found"));

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        });

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

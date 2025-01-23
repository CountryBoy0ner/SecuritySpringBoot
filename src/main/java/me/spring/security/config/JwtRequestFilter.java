package me.spring.security.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import me.spring.security.utils.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+authHeader);
            jwt = authHeader.substring(7);
            try {
                username = jwtTokenUtils.getUsernameFromToken(jwt);
            } catch (ExpiredJwtException e) {
                log.debug("время жизни токена вышло");
            } catch (SignatureException e) {
                log.debug("неправильная подпись токена");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    username, null, jwtTokenUtils.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }
}

//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class JwtRequestFilter extends OncePerRequestFilter {
//    private final JwtTokenUtils jwtTokenUtils;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        String username = null;
//        String jwt = null;
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            jwt = authHeader.substring((7));
//            try {
//                username = jwtTokenUtils.getUsernameFromToken(jwt);  // todo path-> com,spring.security.exceptions.GlobalExceptionHandler
//            } catch (ExpiredJwtException e) {
//                log.debug("время жизни токена вышло");
//            } catch (SignatureException e) {
//                log.debug("неправильная подпись токена");
//            }
//        }
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            log.debug("Аутентификация пользователя: {}", username);
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                    username,
//                    null,
//                    jwtTokenUtils.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
//
//            );
//            SecurityContextHolder.getContext().setAuthentication(token);
//        }
//        filterChain.doFilter(request, response);
//
//    }
//    // перекладывает данные с токена в спринг контекст
//    // todo сделать валидацию с бд
//}

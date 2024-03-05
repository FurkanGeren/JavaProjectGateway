package usermanagenentmodule.usermanagement.Config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import usermanagenentmodule.usermanagement.Util.JwyUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {


    @Autowired
    private JwyUtil jwyUtil;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = extractJwtFromRequest(request);

        if (jwt != null) {
            String roles = jwyUtil.getRoleFromToken(jwt);

            if (roles.contains("Role_Admin")) {
                // If the user has the admin role, set up authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(null, null, Collections.singleton(new SimpleGrantedAuthority("Role_Admin")));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        // Extract JWT token from the request, you need to implement this method according to your token extraction logic
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

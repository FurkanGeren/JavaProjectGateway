package com.gateway.gateway.filter;

import com.gateway.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
public class RoleBasedAuthorizationFilter extends AbstractGatewayFilterFactory<RoleBasedAuthorizationFilter.Config> {

    @Autowired
    private RestTemplate template;
    @Autowired
    private JwtUtil jwtUtil;
    public RoleBasedAuthorizationFilter() {
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if(token != null && token.startsWith("Bearer ")){
                String role = jwtUtil.getRoleFromToken(token.substring(7));

                String[] rolesArray = role.split(",\\s*");
                 boolean isAdmin = Arrays.asList(rolesArray).contains("Role_Admin");

                if(isAdmin){
                    return chain.filter(exchange);
                }
            }

            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        };
    }


    public static class Config {

    }
}

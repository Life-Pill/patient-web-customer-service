package com.lifepill.customerservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Feign client configuration for inter-service communication.
 * Propagates JWT token from incoming request to outgoing Feign calls.
 */
@Slf4j
@Configuration
public class FeignConfig {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * Request interceptor that forwards the Authorization header to Feign calls.
     * This allows inter-service calls to be authenticated with the same token.
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                ServletRequestAttributes attributes = 
                        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                
                if (attributes != null) {
                    HttpServletRequest request = attributes.getRequest();
                    String authHeader = request.getHeader(AUTHORIZATION_HEADER);
                    
                    if (authHeader != null && !authHeader.isEmpty()) {
                        template.header(AUTHORIZATION_HEADER, authHeader);
                        log.debug("Propagated Authorization header to Feign request: {}", 
                                template.url());
                    }
                }
            }
        };
    }
}

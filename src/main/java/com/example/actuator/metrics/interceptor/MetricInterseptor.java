package com.example.actuator.metrics.interceptor;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@AllArgsConstructor
public class MetricInterseptor implements HandlerInterceptor {
    private MeterRegistry registry;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String URI = request.getRequestURI();
        String METHOD = request.getMethod();
        if (!URI.contains("prometheus")) {
            log.info("PATH: {}", URI);
            log.info("METHOD: {}", METHOD);
            String pathKey = "api_".concat(METHOD.toLowerCase()).concat(URI.replace("/", "_").toLowerCase());
            registry.counter(pathKey).increment();
        }
    }
}

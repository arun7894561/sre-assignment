package com.usedetails.project.filter;

import jakarta.servlet.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        String requestId = UUID.randomUUID().toString();

        MDC.put("requestId",requestId);

        long start = System.currentTimeMillis();

        chain.doFilter(request,response);

        long latency = System.currentTimeMillis() - start;

        System.out.println("RequestID="+requestId+
                " Latency="+latency+"ms");

        MDC.clear();
    }
}
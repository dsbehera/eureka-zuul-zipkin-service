package com.dev.zuul.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;

public class ErrorFilter extends ZuulFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(PreFilter.class);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        LOGGER.info("Inside Route Filter");
        return null;
    }
}

package org.michaelliu.web.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by Michael on 7/11/16.
 */
public class ParameterHandleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            String param = servletRequest.getParameter("param");
            if (StringUtils.isEmpty(param)) {
                servletRequest.setAttribute("errorMessage", "no parameter available");
                servletRequest.getRequestDispatcher("/WEB-INF/views/filter/error.jsp")
                        .forward(servletRequest, servletResponse);
            }
            if (param.equals("ok")) {
                servletRequest.setAttribute("isok", "that is ok");
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception ex) {
            servletRequest.setAttribute("errorMessage", ex);
            servletRequest.getRequestDispatcher("/WEB-INF/views/filter/error.jsp")
                    .forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

}

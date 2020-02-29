package com.epam.traffic.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * PageFilter is a filter that checks the matching of the current session and the page on which the user is located.
 * If there is no match, then the user is redirected to the authorization page
 */
public class PageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * method doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
     * is a method that retrieves a session and page from a request, and, if there is a match,
     * redirects to the desired page. Otherwise, redirect to the login page.
     * @param servletRequest request from servlet
     * @param servletResponse response to servlet
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        int userRole;
        String path;
        String [] paths = request.getRequestURI().split("/");
        if(paths[paths.length - 1].lastIndexOf(".html")>0) {
            path = paths[paths.length - 1];
            if (session != null) {
                userRole = (int) session.getAttribute("user_role");
                if (userRole == 1 && path.equalsIgnoreCase("DispatcherPage.html")) {
                    filterChain.doFilter(request, response);
                    return;
                } else if (userRole == 2 && path.equalsIgnoreCase("HRDepartment.html")) {
                    filterChain.doFilter(request, response);
                    return;
                } else if (userRole == 3 && path.equalsIgnoreCase("ReferenceBook.html")) {
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    response.sendRedirect(request.getContextPath() + "/");
                    return;
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }
        } else  {
            filterChain.doFilter(request, response);
            return;
        }
    }

    @Override
    public void destroy() {
    }
}

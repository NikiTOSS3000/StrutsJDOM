package com.epam.struts.presentation.servlet;

import javax.servlet.ServletException;
import org.apache.struts.action.ActionServlet;

public final class JDOMServlet extends ActionServlet {
    private static String path;

    @Override
    public void init() throws ServletException {
        super.init(); 
        path = getServletContext().getRealPath("/");
    }

    public static String getPath() {
        return path;
    }
}

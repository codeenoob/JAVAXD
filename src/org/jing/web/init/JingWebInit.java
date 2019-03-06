package org.jing.web.init;

import org.jing.core.lang.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-03-06 <br>
 */
public class JingWebInit extends HttpServlet {
    public JingWebInit() {
        super();
    }

    @Override
    public void init() throws ServletException {
        Configuration.getInstance();
    }
}

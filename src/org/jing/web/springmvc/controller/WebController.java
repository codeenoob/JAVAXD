package org.jing.web.springmvc.controller;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jing.core.util.ToUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-03-11 <br>
 */
@Controller
public class WebController {
    private static Logger logger = Logger.getLogger(WebController.class);

    @RequestMapping(value = "/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("HelloController start...");
        JSONObject json = ToUtil.getRequestParameters(request);
        ObjectMapper mapper = new ObjectMapper();
        logger.debug("HelloController end...");
        response.getWriter().println(mapper.writeValueAsString(json));
    }
    @RequestMapping(value = "/hello.do")
    public void hello_(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("HelloController start...");
        JSONObject json = ToUtil.getRequestParameters(request);
        ObjectMapper mapper = new ObjectMapper();
        logger.debug("HelloController end...");
        response.getWriter().println(mapper.writeValueAsString(json));
    }
    @RequestMapping(value = "/hello/{serviceName}")
    public void hello__(HttpServletRequest request, HttpServletResponse response, @PathVariable String serviceName) throws Exception {
        logger.debug("HelloController start...");
        JSONObject json = ToUtil.getRequestParameters(request);
        ObjectMapper mapper = new ObjectMapper();
        logger.debug("HelloController end...");
        response.getWriter().println(mapper.writeValueAsString(json));
    }
    // @RequestMapping(value = "callService")
    public void hello___(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("HelloController start...");
        JSONObject json = ToUtil.getRequestParameters(request);
        ObjectMapper mapper = new ObjectMapper();
        logger.debug("HelloController end...");
        response.getWriter().println(mapper.writeValueAsString(json));
    }
}

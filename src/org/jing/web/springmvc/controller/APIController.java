package org.jing.web.springmvc.controller;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jing.core.lang.ExceptionHandler;
import org.jing.core.lang.JingException;
import org.jing.core.util.ToUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-03-21 <br>
 */
@Controller
public class APIController {
    private static Logger logger = Logger.getLogger(APIController.class);

    @RequestMapping(value = "callService")
    public void callService(HttpServletRequest request, HttpServletResponse response) throws JingException {
        ExceptionHandler.publish("WEB-API-0000", "Empty API Request");
    }

    @RequestMapping(value = "callService/{serviceName}")
    public void callService(HttpServletRequest request, HttpServletResponse response, @PathVariable String serviceName)
        throws JingException {
        try {
            JSONObject json = ToUtil.getRequestParameters(request);
        }
        catch (Exception e) {
            ExceptionHandler.publish("WEB-API-0001",
                new StringBuilder("Failed to deal API: [callService: ").append(serviceName).append("]").toString());
        }
    }
}

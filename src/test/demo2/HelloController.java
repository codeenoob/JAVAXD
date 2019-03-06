package test.demo2;

import org.apache.log4j.Logger;
import org.jing.core.lang.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-03-06 <br>
 */
public class HelloController implements Controller {

    private static Logger logger = Logger.getLogger(HelloController.class);

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
        throws Exception {
        logger.debug("HelloController start...");
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "Hello World~");
        mv.setViewName("/temp.html");
        logger.debug("HelloController end...");
        return mv;
    }
}

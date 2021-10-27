package pl_bartlomiej_swies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Hello", urlPatterns = {"/api/*"})
public class ServletTest extends HttpServlet {

    private static final String PARAM_NAME = "name";
    private static final String LANG_PARAM = "lang";
    private final Logger logger = LoggerFactory.getLogger(ServletTest.class);

    private ServiceTest serviceTest;

    /**
     * Servlet container needs it.
     */
    @SuppressWarnings("unused")
    public ServletTest() {
        this(new ServiceTest());
    }

    ServletTest(ServiceTest serviceTest) {
        this.serviceTest = serviceTest;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Got request with parameters: " + req.getParameterMap());
        var name = req.getParameter(PARAM_NAME);
        var lang = req.getParameter(LANG_PARAM);
        resp.getWriter().write(serviceTest.prepareGreeting(name, lang));
    }
}

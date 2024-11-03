// requires eclipse ide and tomcat server
// after creating two files as mentioned and setting up tomcat, right click after opening the HelloServlet.java file and select run as -> tomcat
// your code is working at http://localhost:8080/projectname/configcontext

// edit web.xml in src/main/webapp/WEB-INF at the bottom of the folder structure in right
// web.xml
// add the below code before the end of file ( before </web-app> )
<servlet>
    <servlet-name>HelloServlet</servlet-name>
    <servlet-class>HelloServlet</servlet-class>
    <init-param>
      <param-name>servletConfigParam</param-name>
      <param-value>This is a ServletConfig parameter</param-value>
    </init-param>
  </servlet>
  <servlet-mapping>
    <servlet-name>HelloServlet</servlet-name>
    <url-pattern>/configcontext</url-pattern>
  </servlet-mapping>
  <context-param>
    <param-name>contextParam</param-name>
    <param-value>This is a ServletContext parameter</param-value>
  </context-param>

// create HelloServlet.java in the src/main/java folder at the starting of folder items

// HelloServlet.java

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/configcontext")
public class HelloServlet extends HttpServlet {
    private String servletConfigParam;
    private String contextParam;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servletConfigParam = config.getInitParameter("servletConfigParam");
        ServletContext context = config.getServletContext();
        contextParam = context.getInitParameter("contextParam");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h3>ServletConfig Parameter</h3>");
        response.getWriter().println("<p>servletConfigParam: " + servletConfigParam + "</p>");
        response.getWriter().println("<h3>ServletContext Parameter</h3>");
        response.getWriter().println("<p>contextParam: " + contextParam + "</p>");
        response.getWriter().println("</body></html>");
    }
}


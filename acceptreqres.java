// requires eclipse ide and tomcat server
// after creating two files as mentioned and setting up tomcat, right click after opening the html file and select run as -> tomcat
// your code is working at http://localhost:8080/projectname/form.html

// create from.html in src/main/webapp at the bottom of the folder structure in right

// form.html
<!DOCTYPE html>
<html>
<body>
    <h2>Enter Your Details</h2>
    <form action="UserServlet" method="POST">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>

// create UserServlet.java in the src/main/java folder at the starting of folder items

// UserServlet.java

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Submitted Information</h2>");
        out.println("<p>Username: " + username + "</p>");
        out.println("<p>Email: " + email + "</p>");
        out.println("</body></html>");
        out.close();
    }
}


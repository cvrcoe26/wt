// requires eclipse ide and tomcat server
// after creating two files as mentioned and setting up tomcat, right click after opening the html file and select run as -> tomcat
// your code is working at http://localhost:8080/projectname/form.html

// create from.html in src/main/webapp at the bottom of the folder structure in right
// form.html
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h2>Login</h2>
    <form action="LoginServlet" method="POST">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>


// create LoginServlet.java in the src/main/java folder at the starting of folder items

// LoginServlet.java

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[][] users = {{"user1", "pwd1"}, {"user2", "pwd2"}, {"user3", "pwd3"}, {"user4", "pwd4"}};
        HttpSession session = request.getSession();
        if (session.isNew()) {
            for (String[] user : users) {
                response.addCookie(new Cookie(user[0], user[1]));
            }
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean authenticated = false;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(username) && cookie.getValue().equals(password)) {
                authenticated = true;
                break;
            }
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (authenticated) {
            out.println("<h2>Welcome, " + username + "!</h2>");
        } else {
            out.println("<h2>You are not an authenticated user</h2>");
        }
        out.close();
    }
}


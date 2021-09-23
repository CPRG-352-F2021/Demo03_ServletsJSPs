
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // this will have the servlet call upon a JSP to be loaded by the client's browser
        getServletContext().getRequestDispatcher("/WEB-INF/helloWorldForm.jsp").forward(request,response);
        // stop the code call
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstname = "";
        String lastname = "";
        // capture the parameters coming in from the POST request
        firstname = request.getParameter("first_name");
        lastname = request.getParameter("last_name");
        
        //System.out.println(firstname + " " + lastname);
        
        // Set some attributes in the request object.
        request.setAttribute("firstName", firstname);
        request.setAttribute("lastName", lastname);
        
        // validation: if the parameters don't exist or are empty, show the first page again.
        if( firstname == null || firstname.equals("") || lastname == null || lastname.equals("") ){
            // set up a helpful error message for the user.
            request.setAttribute("message", "Invalid entry. Please enter both a first and last name.");
            // display the form again.
            getServletContext().getRequestDispatcher("/WEB-INF/helloWorldForm.jsp").forward(request, response);
            // after we load a JSP, use a return statement.
            // stop the code from executing (VERY IMPORTANT!)
            return;
        }

        // The request object will be passed through to the JSP by the forward() method.
        getServletContext().getRequestDispatcher("/WEB-INF/sayHello.jsp").forward(request,response);
        return;
        
    }

}

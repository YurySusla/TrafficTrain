package com.epam.traffic.controllers;

import com.epam.traffic.factories.UserFactory;
import com.epam.traffic.interfaces.UserDAO;
import com.epam.traffic.models.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * UserController is a controller that authorizes the user by the entered login and password and redirects to the necessary resource
 */
public class UserController extends HttpServlet {
    /**
     * method doPost(HttpServletRequest req, HttpServletResponse resp) is a method that receives data
     * from a request for authorization, searches for a user in the database and,
     * if successful, redirects it to the desired resource with the creation of a session
     * @param req is a request from the user
     * @param resp is a response from the user
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * setting request encoding in utf-8
         */
        req.setCharacterEncoding("UTF-8");
        /**
         * setting response encoding in utf-8
         */
        resp.setCharacterEncoding("UTF-8");
        /**
         * login is a variable for storing user login, getting the value from the request
         */
        String login = req.getParameter("login");
        /**
         * password is a variable for storing user password, getting the value from the request
         */
        String password = req.getParameter("password");
        /**
         * userDAO is a factory instance for working with a user table
         */
        UserDAO userDAO = new UserFactory();
        /**
         * inputUser is an object of class User
         */
        User inputUser = new User(login, password);
        /**
         * user this is an object of class User, returned by the method isUserExist(inputUser) of the UserDAO,
         * which compares the user request created from the data with the records in the database,
         * and if successful, returns the received user
         */
        User user = userDAO.isUserExist(inputUser);
        /**
         * path is a variable to hold the resource name for redirection
         */
        String path = null;
        /**
         * If the user exists, then the parameters of the received user are transferred to the
         * session and redirected to the necessary resource, depending on the user group. If not, then stay on the login page
         */
        if (user != null) {
            HttpSession oldSession = req.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            /**
             * newSession is a newly created session
             */
            HttpSession newSession = req.getSession(true);
            newSession.setAttribute("login", user.getLogin());
            newSession.setAttribute("user_id", user.getUserId());
            newSession.setAttribute("user_role", user.getUserGroup().getValue());
            switch (user.getUserGroup().getValue()){
                case 1: path ="/DispatcherPage.html";
                    break;
                case 2 : path = "/HRDepartment.html";
                    break;
                case 3: path = "/ReferenceBook.html";
                    break;
            }
        } else {
            path = "/";
        }
        resp.sendRedirect(req.getContextPath() + path);
        return;
    }

    /**
     * method doGet(HttpServletRequest req, HttpServletResponse resp) is a method that removes all user data
     * from the session and redirects to the authorization page
     * @param req is a request from the user
     * @param resp is a response from the user
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * session is the session received from the request
         */
        HttpSession session = req.getSession(false);
        session.invalidate();
        /**
         * path is a variable to hold the resource name for redirection
         */
        String path = "/";
        resp.sendRedirect(req.getContextPath() + path);
        return;
    }
}

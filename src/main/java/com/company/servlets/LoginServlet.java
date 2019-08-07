package com.company.servlets;

import com.company.Dao.UsersDao;
import com.company.Dao.UsersDaoJDBC;
import com.company.models.User;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties properties = new Properties();
        try {

            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
            String url= properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            String DriverClassName = properties.getProperty("db.Driver");
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setUrl(url);
            dataSource.setDriverClassName(DriverClassName);
            usersDao = new UsersDaoJDBC(dataSource);
        } catch(IOException e){
            throw new IllegalStateException(e);
        }

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usermail = req.getParameter("usermail");
        String password = req.getParameter("password");
        Optional<User> user = usersDao.find(usermail, password);
        if(!user.isEmpty()){
            HttpSession session = req.getSession();
            session.setAttribute("UserStruct", user.get());

            resp.sendRedirect(req.getContextPath()+"/persarea");
        }
        else{

            req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
        }
    }
}
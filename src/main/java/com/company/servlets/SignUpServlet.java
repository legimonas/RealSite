package com.company.servlets;

import com.company.Dao.UsersDaoJDBC;
import com.company.mail.MailSender;
import com.company.models.User;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    UsersDaoJDBC usersDao;


    @Override
    public void init() throws ServletException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties properties = new Properties();
        try{

            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));

            String Password = properties.getProperty("db.password");
            String url = properties.getProperty("db.url");
            String DriverClass = properties.getProperty("db.Driver");
            String Username = properties.getProperty("db.username");


            dataSource.setUrl(url);
            dataSource.setUsername(Username);
            dataSource.setPassword(Password);
            dataSource.setDriverClassName(DriverClass);

            usersDao = new UsersDaoJDBC(dataSource);
        }catch(IOException e) {
            throw new IllegalStateException(e);
        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/signup.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //try {


            String name = req.getParameter("name");
            String usermail = req.getParameter("usermail");
            String Password = req.getParameter("password");
            LocalDate birthdate = LocalDate.parse(req.getParameter("birthdate"));
            if (name.equals("") || usermail.equals("") || Password.equals("")) {
                req.getServletContext().getRequestDispatcher("/jsp/signup.jsp");
            } else {
                User user = new User(usersDao.MaxId() + 1, name, Password, birthdate, usermail);
                user.hashPassword();
                if (usersDao.Save(user)) {
                    //MailSender sender = new MailSender("legimonas1000@gmail.com", "12Ad23Sf34Dg");
                    //sender.SendMessage(usermail, "thank you for registration", "You've registered!!!\nYour name: " + name + "\nBirth Date: " + birthdate);
                    req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
                } else {
                    req.getServletContext().getRequestDispatcher("/jsp/signup.jsp").forward(req, resp);
                }

            }
        //}catch (MessagingException e){
        //    throw new IllegalStateException(e);
        //}
    }
}

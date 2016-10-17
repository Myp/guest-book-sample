package ru.sandbox.gb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by daniil on 15.10.16.
 */
public class GuestBookLoginServlet extends HttpServlet {

    private MessageStorage storage;

    @Override
    public void init() throws ServletException {
        storage = new InMemoryMessageStorage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserInfo User = (UserInfo) session.getAttribute("userInfo");

        if  ((User != null)&(User.getUser()!="")){
            GuestBookLoginRender.renderPage(storage.getMessages(), resp, User.getUser());
        } else{
            resp.sendRedirect("http://localhost:8080/");
        }

        req.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserInfo User = (UserInfo) session.getAttribute("userInfo");

        req.setCharacterEncoding("UTF-8");

        Message newMessage = Message.fromRequest(req, User.getUser());

        if (newMessage != null) {
            storage.store(newMessage);
        }

        GuestBookLoginRender.renderPage(storage.getMessages(), resp, User.getUser());

    }
}

package ru.sandbox.gb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by daniil on 15.10.16.
 */
public class GuestBookServlet extends HttpServlet {

    private MessageStorage storage;

    @Override
    public void init() throws ServletException {
        storage = new InMemoryMessageStorage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GuestBookRender.renderPage(storage.getMessages(), resp);
        req.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        UserInfo User = new UserInfo();
        session.setAttribute("userInfo", User);

        User.login(req.getParameter("user"));

        resp.sendRedirect("http://localhost:8080/login/");

//        Message newMessage = Message.fromRequest(req);
//        if (newMessage != null) {
//            storage.store(newMessage);
//        }
//        GuestBookRender.renderPage(storage.getMessages(), resp);

    }
}

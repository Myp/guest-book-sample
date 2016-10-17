package ru.sandbox.gb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
/**
 * Created by Горбач on 16.10.2016.
 */
public class StaticFilesServlet extends HttpServlet{
    private MessageStorage storage;

    @Override
    public void init() throws ServletException {
        storage = new InMemoryMessageStorage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        String filename = uri.substring("/assets".length());
        InputStream stream = GuestBookServlet.class.getResourceAsStream(filename);
        int b;
        while ((b=stream.read())!=-1) {
            resp.getOutputStream().write(b);
        }
        req.setCharacterEncoding("UTF-8");
    }

}

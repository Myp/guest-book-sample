package ru.sandbox.gb;

        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;
        import java.io.OutputStream;
        import java.nio.charset.Charset;
        import java.util.List;

/**
 * Created by daniil on 15.10.16.
 */
public class GuestBookLoginRender {

    private GuestBookLoginRender() {
    }

    public static void renderPage(List<Message> message, HttpServletResponse resp, String username) throws IOException {

        resp.setContentType("text/html");
        OutputStream os = resp.getOutputStream();

        StringBuilder sb = new StringBuilder();

        sb.append("<!doctype html>");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<meta charset='UTF-8'>");
        sb.append("<title> Guest Book </title>");
        sb.append("<link href='http://localhost:8080/assets/Style.css' rel='stylesheet' type='text/css' /><head>");

        sb.append("<body style='background-color: #cccccc'>");
        sb.append("<div class='header'>");
        sb.append("<div class='img'><img src='http://localhost:8080/assets/kittySmall.png'/></div>");
        sb.append("<div class='title'>Guest Book</div>");
        sb.append("<div class='end'></div></div>");


        sb.append("<div class='menu'>");
        sb.append("<div class='name'>");
        sb.append(username).append("</div>");
        sb.append("<form method='POST'>");
        sb.append("<div><textarea name='body' id='body' placeholder='Ваше сообщение'></textarea></div>");
        sb.append("<div><input type='submit' value='Отправить' id='button'/></div></div>");
        sb.append("</form>");

        renderMessages(sb, message);

        sb.append("</body>");

        os.write(sb.toString().getBytes(Charset.forName("UTF-8")));
        os.close();
    }

    private static void renderMessages(StringBuilder sb, List<Message> message) {
        if(message.isEmpty()) {
            sb.append("<div class='empty'><p>Пока пусто!</p><div>");
        } else {
            for (Message m : message) {
                renderMessage(sb, m);
            }
        }
    }

    private static void renderMessage(StringBuilder sb, Message message) {
        sb.append("<div class='pages'>");
        sb.append("<div class='top'>");
        sb.append("<div class='name'>");
        sb.append(message.getUser()).append("</div>");
        sb.append("<div class='time'>");
        sb.append(message.getTime()).append("</div>");
        sb.append("<div class='endfloat'></div></div>");
        sb.append("<div class='text'>");
        sb.append(message.getBody()).append("</div></div>");
    }

}

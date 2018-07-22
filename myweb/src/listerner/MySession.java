package listerner;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySession implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        int c = 0;
        Object obj =  se.getSession().getServletContext().getAttribute("count");
        if (obj != null){
            c = Integer.parseInt(String.valueOf(obj));
        }
        c++;
        System.out.println("第" + c + "次会话！");
        se.getSession().getServletContext().setAttribute("count", c);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("再见，第" + se.getSession().getServletContext().getAttribute("count") + "次会话的人！");
    }
}

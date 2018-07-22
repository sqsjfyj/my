package listerner;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyListerner implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("再见，第" + sre.getServletContext().getAttribute("count") + "次访问的人！");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        int c = 0;
        Object obj =  sre.getServletContext().getAttribute("count");
        if (obj != null){
            c = Integer.parseInt(String.valueOf(obj));
        }
        c++;
        System.out.println("第" + c + "次访问！");
        //System.out.println(Integer.valueOf("的"));
        sre.getServletContext().setAttribute("count", c);
    }
}

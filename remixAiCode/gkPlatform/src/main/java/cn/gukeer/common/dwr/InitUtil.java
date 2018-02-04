package cn.gukeer.common.dwr;

import cn.gukeer.platform.persistence.entity.User;
import org.directwebremoting.Container;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;
import org.directwebremoting.servlet.DwrServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 * Created by lx on 2017/3/3.
 */
public class InitUtil extends DwrServlet {
    @Override
    public void init() throws ServletException {
        Container container = ServerContextFactory.get().getContainer();
        // 工厂方法get()返回ServerContext实例
        ScriptSessionManager manager = container.getBean(ScriptSessionManager.class);
        ScriptSessionListener listener = new ScriptSessionListener() {
            public void sessionCreated(ScriptSessionEvent ev) {
                HttpSession session = WebContextFactory.get().getSession();
                User user = (User) session.getAttribute("loginUser");
                ev.getSession().setAttribute("loginRefId",  user.getRefId());
            }
            public void sessionDestroyed(ScriptSessionEvent ev) {
                ev.getSession().removeAttribute("loginRefId");
            }
        };
        manager.addScriptSessionListener(listener);
    }
    }


package cn.gukeer.common.dwr;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

import javax.servlet.ServletException;
import java.util.Collection;
import java.util.List;

public class NotifyJs {
    public void send(final String msg,final List<String> uid) {

        //过滤器
        ScriptSessionFilter filter = new ScriptSessionFilter() {
            public boolean match(ScriptSession scriptSession) {
                Object userid= scriptSession.getAttribute("loginRefId");
                if (userid == null)
                    return false;
                return uid.contains(userid.toString());
            }
        };


        Runnable run = new Runnable() {
            private ScriptBuffer script = new ScriptBuffer();

            @Override
            public void run() {
                script.appendCall("show", msg);

                Collection<ScriptSession> sessions = Browser.getTargetSessions();

                for (ScriptSession scriptSession : sessions) {
                    scriptSession.addScript(script);
                }

            }
        };
        Browser.withAllSessionsFiltered(filter, run);    //注意这里调用了有filter功能的方法

    }

    public void init() throws ServletException {
        InitUtil initUtil = new InitUtil();
        initUtil.init();
    }


}

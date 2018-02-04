package cn.gukeer.common.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * Created by conn on 2016/7/29.
 */
public class WebSessionManager extends DefaultWebSessionManager {

    private ThreadLocal<ServletRequest> currServletRequest = new ThreadLocal<ServletRequest>();

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        currServletRequest.set(request);

        return super.retrieveSession(sessionKey);
    }

    @Override
    protected Session retrieveSessionFromDataSource(Serializable sessionId) throws UnknownSessionException {
        ServletRequest request = currServletRequest.get();
        currServletRequest.remove();
        if (request != null) {
            Object s = request.getAttribute(sessionId.toString());
            if (s != null) {
                return (Session) s;
            }
        }

        Session s = super.retrieveSessionFromDataSource(sessionId);
        if (request != null) {
            request.setAttribute(sessionId.toString(), s);
        }
        return s;
    }
}

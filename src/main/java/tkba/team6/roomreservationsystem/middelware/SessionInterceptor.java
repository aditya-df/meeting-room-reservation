package tkba.team6.roomreservationsystem.middelware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tkba.team6.roomreservationsystem.db.model.user.UserAccess;
import tkba.team6.roomreservationsystem.db.model.user.UserAccessAdmin;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserAccess UserAccess;

    @Autowired
    private UserAccessAdmin UserAccessAdmin;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception  {
        HttpSession session = request.getSession(false);

        try {
            if (session.getAttribute("UserLoginId") == null) {
                throw new NullPointerException("");
            }
            
            Long UserLoginId = (Long) session.getAttribute("UserLoginId");
            request.setAttribute("customAttribute", "Helol from interceptor!");
        }
        catch(NullPointerException e) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        return true;
    }
}
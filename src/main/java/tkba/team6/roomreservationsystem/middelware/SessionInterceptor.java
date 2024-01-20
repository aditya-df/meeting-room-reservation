package tkba.team6.roomreservationsystem.middelware;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception  {
        HttpSession session = request.getSession(false);

        try {
            if (session.getAttribute("UserLoginId") == null) {
                throw new NullPointerException("");
            }
        }
        catch(NullPointerException e) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        return true;
    }
}
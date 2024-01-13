package tkba.team6.roomreservationsystem.middelwear;

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
            if (session.getAttribute("username") == null) {
                throw new NullPointerException("");
            }

            request.setAttribute("customAttribute", "Hello from interceptor!");
        }
        catch(NullPointerException e) {
            response.sendRedirect(request.getContextPath() + "/login");
        }

        return true;
    }
}
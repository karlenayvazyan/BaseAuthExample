package am.ak.base.auth.interceptor;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    private final String username = "test";
    private final String password = "newUser";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("authorization");
        if (authorization == null || authorization.isBlank()) return false;

        String[] authParts = authorization.split("\\s+");

        if (authParts.length < 1) return false;

        byte[] decode = new Base64().decode(authParts[1]);

        String decodedString = new String(decode);

        if (!decodedString.equals(String.format("%s:%s", username, password))) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().println("Incorrect username and password");
            return false;
        }
        return true;
    }
}

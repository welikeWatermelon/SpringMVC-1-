package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet { // 서블릿은 HttpServlet을 상속받아야함


    // Cltr + O 해서 service -> 자물쇠 잠겨 있는거 하면
    // service가 호출됨
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // http://localhost:8080/hello?username=kim
        // ?username=kim 쿼리파라미터

        String username = request.getParameter("username");// ctrl + alt + v (우측 만들고 자동완성)
        System.out.println("username = " + username);

        response.setContentType("text/plain"); // 단순 문자열이다 라고 알려주는 것
        response.setCharacterEncoding("utf-8"); // 인코딩 정보
        response.getWriter().write("hello "+ username); // http 메시지 바디에 데이터가 들어감

    }
}

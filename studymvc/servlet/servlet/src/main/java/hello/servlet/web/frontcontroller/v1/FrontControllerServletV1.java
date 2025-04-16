package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
// 모든 요청을 한 곳에서 받아서, 알맞은 컨트롤러(ControllerV1 구현체)에게 위임하는 역할!
// 즉, "너 이 URL이면 누구한테 보내야 해!"
// 라고 판단해서 실행까지 책임지는 요청 분배 총괄 서블릿이옵니다.

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller")
public class FrontControllerServletV1 extends HttpServlet {
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1(){
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members/members", new MemberListControllerV1());
    }

    // 현재 서블릿이 각기 다른 요청에 따라 컨트롤러를 부름
    // 요청이 들어오면 service가 실행되고 URL 경로에 따라 controller에 해당 컨트롤러가 저장됨


    // service라는 메서드는 요청이 발생하면 자동으로 실행
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        // 현재 사용자가 요청한 URL 경로를 가져옵니다.
        String requestURI = request.getRequestURI();


        ControllerV1 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response);
        // 찾은 컨트롤러에게 요청을 위임합니다.
    }
}

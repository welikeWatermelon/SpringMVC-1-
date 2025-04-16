package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3(){
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 요청 URL을 받아서
        String requestURI = request.getRequestURI();

        // 해당 컨트롤러를 가져와
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // request 정보를 Map으로 바꾸는 과정
        Map<String, String> paramMap = createParamMap(request);

        // paramap을 컨트롤러에게 전달해서 컨트롤러는 처리결과로 view(viewname + model)를 반환
        ModelView mv = controller.process(paramMap);

        // view 이름 뽑아
        String viewName = mv.getViewName();

        // view이름 -> 실제 경로로 바꿔줌
        MyView view = viewResolver(viewName);

        // 해당 경로에다가 정보를 전달하는 것
        // mv.getModel()는 데이터바구니.. -> 예) new Member("영준",25)
        view.render(mv.getModel(), request, response);
        // 컨트롤러가 만든 **데이터(Model)**를
        // JSP에게 전달하고,
        // 그 JSP를 실행해서 최종 HTML 응답을 만드는 명령
    }

    // request 정보를 Map으로 바꾸는 메서드
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator().
                forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));

        return paramMap;
    }

    // view이름 -> 실제 경로로 바꿔주는 메서드
    private MyView viewResolver(String viewName){
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}

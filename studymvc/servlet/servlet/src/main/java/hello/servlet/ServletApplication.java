package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 스프링이 자동으로 현재 패키지의 하위 패키지를 모두 뒤져서 서블릿을 찾아서 자동으로 등록해줌 (서블릿 자동 등록)
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

}

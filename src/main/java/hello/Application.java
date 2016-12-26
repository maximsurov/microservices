package hello;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("/")
    public String home(HttpServletRequest request) {
        return "Hello from: " + request.getRemoteAddr() + ", " + request.getRemoteHost() + ", " + 
        		request.getLocalAddr() + ", " + request.getServerName();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

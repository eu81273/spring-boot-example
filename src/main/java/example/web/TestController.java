package example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
public class TestController {

    /*
        @RequestMapping annotation 을 사용해서 특정 경로에 대한 response 를 지정할 수 있다.
        @ResponseBody annotation 을 사용하면 ViewResolver 를 거치지 않고 HTTP Response Body 에 직접 쓰여지게 된다.
        쓰여지는 값은 리턴되는 데이터 타입에 따라 MessageConverter 에서 변환을 거치게 된다.
     */

    @RequestMapping("/test")
    @ResponseBody
    public String test () {
        return "이것은 /test 페이지입니다.";
    }

    @RequestMapping("/json/{name}")
    @ResponseBody
    public Person json (@PathVariable String name) {
        Person person = new Person();
        person.setName(name);
        person.setAge(50);
        return person;
    }

    @RequestMapping("/")
    public String home(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", "Hello World");
        model.put("title", "Hello App");
        return "home";
    }
}

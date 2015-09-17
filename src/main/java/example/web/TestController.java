package example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test () {

        return "이것은 /test 페이지입니다.";
    }

    @RequestMapping("/")
    public String home(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", "Hello World");
        model.put("title", "Hello App");

        return "home";
    }
}

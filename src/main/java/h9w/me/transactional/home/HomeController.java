package h9w.me.transactional.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {


    @GetMapping("/test")
    public void homeTest(@RequestParam Map<String, String> params) {
        params.forEach((k, v) -> System.out.println("key : " + k + " v : " + v));

    }



}

package net.sonien.studio.springbootstudy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/")
    public String root() {
        return "redirect:/questions";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String HelloWorld() {
        return "Hello World";
    }
}

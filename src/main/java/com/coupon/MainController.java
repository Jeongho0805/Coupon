package com.coupon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String mainPage() {
        log.info("메인페이지 접근");
        return "/views/main.html";

    }
}

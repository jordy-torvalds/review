package com.kis.searchaddress.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Main 화면 컨트롤러
 */
@RestController
@Slf4j
public class ViewController {

    @GetMapping("/main")
    public ModelAndView mainView() {
        return new ModelAndView("main");
    }
}

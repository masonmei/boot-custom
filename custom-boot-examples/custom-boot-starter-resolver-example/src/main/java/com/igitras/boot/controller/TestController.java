package com.igitras.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mason on 11/18/15.
 */
@RestController
public class TestController {

    @RequestMapping("test")
    public String test(Page page){
        return page.toString();
    }
}

package com.interview.spring.simpleimpl.testspring;

import com.interview.spring.simpleimpl.annotation.Controller;
import com.interview.spring.simpleimpl.annotation.mvc.RequestMapping;
import com.interview.spring.simpleimpl.annotation.mvc.ResponseBody;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 02:30
 */
@Controller
@RequestMapping
public class HelloWaorldController {
    @RequestMapping
    @ResponseBody
    public String hello() {
        return "hello doodle";
    }
}

package com.factory.manager.domain.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.factory.manager.core.constants.Endpoints.DEMO;

@RestController
@RequestMapping(DEMO)
public class DemoController {
    @GetMapping
    public String welcome() {
        return "Welcome to the factory manager app";
    }

}

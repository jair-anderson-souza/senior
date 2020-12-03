package io.github.jass2125.senior.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "url")
@RestController
public class UrlController {

    @GetMapping
    public Url get() {
        return Url.builder().currentUrl("sdfsd").newUrl("sdfsdf").build();

    }

}

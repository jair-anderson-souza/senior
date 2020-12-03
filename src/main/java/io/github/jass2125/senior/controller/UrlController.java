package io.github.jass2125.senior.controller;

import com.sun.istack.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "url")
@RestController
public class UrlController {

    @GetMapping
    public Url get(@NotNull @RequestBody String url) {
        System.out.println(url);
        return Url.builder().currentUrl(url).newUrl("sdfsdf").build();

    }

}

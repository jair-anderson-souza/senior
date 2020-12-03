package io.github.jass2125.senior.controller;

import com.sun.istack.NotNull;
import io.github.jass2125.senior.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "url")
@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    @GetMapping
    public String get(@NotNull @RequestBody String url) {
        var newUrl = this.urlService.encoutUrl(url);
        return newUrl;

    }

}

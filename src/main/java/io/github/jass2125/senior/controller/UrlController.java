package io.github.jass2125.senior.controller;

import io.github.jass2125.senior.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestMapping(value = "url")
@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    //add swagger

    @GetMapping
    public String get(@NotNull @Valid @RequestBody UrlRequest url) {
        var newUrl = this.urlService.encoutUrl(url.getUrl());
        return newUrl;

    }

}

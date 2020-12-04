package io.github.jass2125.senior.controller;

import io.github.jass2125.senior.controller.request.URLRequest;
import io.github.jass2125.senior.domain.model.Url;
import io.github.jass2125.senior.service.URLService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping(value = "url")
@RestController
@Api(value = "URL manager")
public class URLController {

    @Autowired
    private URLService urlService;

    //add swagger
    @ApiOperation(value = "Transform url")
    @PostMapping
    public String get(@NotNull @Valid @RequestBody @ApiParam URLRequest url) {
        var newUrl = this.urlService.encoutUrl(url.getUrl());
        return newUrl;

    }

}

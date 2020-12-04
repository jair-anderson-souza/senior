package io.github.jass2125.senior.controller;

import io.github.jass2125.senior.controller.request.URLRequest;
import io.github.jass2125.senior.controller.response.URLResponse;
import io.github.jass2125.senior.service.URLService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RequestMapping(value = "url")
@RestController
@Api(value = "URL manager")
public class URLController {

    @Autowired
    private URLService urlService;

    @ApiOperation(value = "Transform url", response = URLResponse.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public URLResponse get(@NotNull @Valid @RequestBody @ApiParam URLRequest url) {
        var newUrl = this.urlService.encoutUrl(url.getUrl());
        return newUrl;
    }

    //consertar isso aqui. tá com erro
    @ApiOperation(value = "Redirect user", response = String.class)
    @RequestMapping(value = "/{shortURL}")
    public ResponseEntity<Object> redirectUser(@NotNull @NotEmpty @ApiParam @PathVariable("shortURL") String shortURL) {
        String newUrl = this.urlService.findByShortUrl(shortURL).get().getUrl();
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(newUrl)).build();
    }

}

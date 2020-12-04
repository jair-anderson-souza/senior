package io.github.jass2125.senior.controller;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UrlRequest {

    @NotNull
    @NotEmpty
    private String url;


}

package io.github.jass2125.senior.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class URLRequest {

    @NotNull
    @NotEmpty
    private String url;


}

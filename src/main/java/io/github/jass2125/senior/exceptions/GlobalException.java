package io.github.jass2125.senior.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class GlobalException {

    private HttpStatus status;
    private String mensagem;
    private List<String> erros;


}


package io.github.jass2125.senior.service;

import io.github.jass2125.senior.exceptions.URLShorterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UrlShortener {


    public String encurtarUrl(String url) {
        try {
            log.info("M=encoutUrl, m=init encourt url, shortURL={}", url);
            return "sdfsdf";
        } catch (Exception e) {
            throw new URLShorterException("");
        }
    }

}

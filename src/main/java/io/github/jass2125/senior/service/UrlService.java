package io.github.jass2125.senior.service;

import io.github.jass2125.senior.domain.model.Url;
import io.github.jass2125.senior.exceptions.UrlAlreadyExists;
import io.github.jass2125.senior.repositories.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;


    //add tests
    //enable redis
    public String encoutUrl(String url) {
        log.info("M=encoutUrl, m=init encourt url, url={}", url);
        Optional<Url> uri = this.urlRepository.findByCurrentUrl(url);
        if (uri.isPresent()) {
            log.error("m=Url already exists, url={}", url);
            throw new UrlAlreadyExists("Url already exists!!");
        }

        //adicionar logs
        String newUrl = encurtarUrl(url);
        Url uriSave = Url.builder().currentUrl(url).newUrl(newUrl).build();
        this.urlRepository.save(uriSave);
        return newUrl;
    }

    //criar encurtador de url
    //jogar isso em outro componente
    public String encurtarUrl(String url) {
        return "url encurtada";
    }
}

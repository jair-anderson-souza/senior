package io.github.jass2125.senior.service;

import io.github.jass2125.senior.controller.response.URLResponse;
import io.github.jass2125.senior.domain.model.Url;
import io.github.jass2125.senior.exceptions.UrlAlreadyExists;
import io.github.jass2125.senior.repositories.URLRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class URLService {

    @Autowired
    private URLRepository urlRepository;

    @Autowired
    private URLShortener urlShortener;

    //add tests
    public URLResponse encoutUrl(String url) {
        log.info("M=encoutUrl, m=init encourt url, shortURL={}", url);

        checkIfURLAlreadyExists(url);

        String newUrl = this.urlShortener.encurtarUrl(url);

        Url uriSave = Url
                .builder()
                .url(url)
                .shortUrl(newUrl)
                .build();

        save(uriSave);

        return URLResponse.builder().url(newUrl).build();
    }


    @Cacheable(cacheNames = "URLs", key = "#url")
    public Optional<Url> findByUrl(String url) {
        log.info("M=findByUrl, m=init find by url, url={}", url);
        return this.urlRepository.findByUrl(url);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Url uriSave) {
        log.info("M=save, m=init find by url, url={}", uriSave);
        this.urlRepository.save(uriSave);
    }

    public void checkIfURLAlreadyExists(String url) {
        log.info("M=checkIfShortURLAlreadyExists, m=init check if URL already exists, url={}", url);
        Optional<Url> urlSearched = findByUrl(url);
        if (urlSearched.isPresent()) {
            log.error("m=Url already exists, url={}", url);
            throw new UrlAlreadyExists("Url already exists!!");
        }
    }

}

package io.github.jass2125.senior.service;

import io.github.jass2125.senior.domain.model.Url;
import io.github.jass2125.senior.exceptions.UrlAlreadyExists;
import io.github.jass2125.senior.repositories.URLRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class URLService {

    @Autowired
    private URLRepository urlRepository;

    @Autowired
    private UrlShortener urlShortener;

    public List<Url> findAll() {
        return urlRepository.findAll();
    }

    //add tests
    public String encoutUrl(String url) {
        log.info("M=encoutUrl, m=init encourt url, shortURL={}", url);

        checkIfShortURLAlreadyExists(url);

        //adicionar logs
        String newUrl = this.urlShortener.encurtarUrl(url);
        Url uriSave = Url
                .builder()
                .url(url)
                .shortUrl(newUrl)
                .build();

        save(uriSave);

        return newUrl;
    }


    @Cacheable(cacheNames = "URLs", key = "#url")
    public Optional<Url> findByUrl(String url) {
        log.info("M=findByUrl, m=init find by url, url={}", url);
        return this.urlRepository.findByCurrentUrl(url);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Url uriSave) {
        this.urlRepository.save(uriSave);
    }

    public void checkIfShortURLAlreadyExists(String url) {
        log.info("M=checkIfShortURLAlreadyExists, m=init check if short URL already exists, url={}", url);
        Optional<Url> urlSearched = findByUrl(url);
        if (urlSearched.isPresent()) {
            log.error("m=Url already exists, url={}", url);
            throw new UrlAlreadyExists("Url already exists!!");
        }
    }

}

package com.url.shortener.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.url.shortener.service.repository.URLRepository;
import com.url.shortener.service.repository.entity.URLTABLE;
import com.url.shortener.service.response.UrlServiceResponse;
import com.url.shortener.service.service.ShortUrlAPIServiceRSA65;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static java.util.Objects.hash;

@Slf4j
@RestController
@RequestMapping("/url-shortener")
public class URLShortenerRestController {

    @Autowired private URLRepository urlRepository;
    @Autowired private ShortUrlAPIServiceRSA65 shortUrlAPIServiceRSA65;
    @Autowired private UrlServiceResponse urlServiceResponse;

    @RequestMapping(value = "shortRSA65", method = RequestMethod.POST)
    public ResponseEntity<URLTABLE> shortURLUsingRSA65(@RequestBody String originalURL){
        log.info("Input = {}",new Gson().toJson(originalURL));
        String id = Integer.toString(hash(originalURL));
        Optional<URLTABLE> entry = urlRepository.findById(id);
        URLTABLE tableRow;
        if(entry.isPresent()){
            log.info("URL with ID :{} already exists",id);
            tableRow = entry.get();
        } else {
            tableRow = URLTABLE.builder().id(id).originalUrl(originalURL).shortUrl(shortUrlAPIServiceRSA65.shortUrl(originalURL,id))
                    .build();
            urlRepository.save(tableRow);
        }
        log.debug("Short URL = {}",tableRow);
        return urlServiceResponse.return202(tableRow);
    }
}

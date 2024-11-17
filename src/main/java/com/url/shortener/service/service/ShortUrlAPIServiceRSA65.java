package com.url.shortener.service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@Slf4j
public class ShortUrlAPIServiceRSA65 implements ShortUrlAPI<String> {

    @Override
    public String shortUrl(String originalUrl, String id) {
        try {
            URI uri =  URI.create(originalUrl);
            log.info("Path = {}, query = {}",uri.getPath(),uri.getQuery());
            return uri.getPath()+"/"+id;
        } catch (Exception e) {
            log.error("Error in shortening the URL, original URL : {}, error : {}", originalUrl,e.getMessage());
        }
        return "";
    }
}

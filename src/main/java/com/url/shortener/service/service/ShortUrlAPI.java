package com.url.shortener.service.service;

public interface ShortUrlAPI<T> {
    T shortUrl(String originalUrl, String id);
}

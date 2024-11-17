package com.url.shortener.service.response;

import com.url.shortener.service.repository.entity.URLTABLE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UrlServiceResponse {
    public ResponseEntity<URLTABLE> return202(URLTABLE responseBody){
        return ResponseEntity.accepted().contentType(MediaType.APPLICATION_JSON).body(responseBody);
    }

    public ResponseEntity<String> return400(){
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body("Bad Request");
    }
}

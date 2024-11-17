package com.url.shortener.service.repository;

import com.url.shortener.service.repository.entity.URLTABLE;
import org.springframework.data.repository.CrudRepository;

public interface URLRepository extends CrudRepository<URLTABLE,String> {

}

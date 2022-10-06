package com.example.demomusor.repository;

import com.example.demomusor.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);


}

package com.example.company.message.repository;

import com.example.company.message.model.Message;
import java.math.BigInteger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, BigInteger> {

}

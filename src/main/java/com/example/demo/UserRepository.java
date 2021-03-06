package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String s);
}

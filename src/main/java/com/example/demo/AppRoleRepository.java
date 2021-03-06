package com.example.demo;

import com.example.demo.models.AppRole;
import org.springframework.data.repository.CrudRepository;

public interface AppRoleRepository extends CrudRepository<AppRole, Long> {
    AppRole findByRole(String role);
}
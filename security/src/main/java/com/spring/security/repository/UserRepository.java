package com.spring.security.repository;


import com.spring.security.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends  JpaRepository<Users,Integer> {
  Users findByName(String name);
}
package com.ly.Resolute.repository;

import com.ly.Resolute.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Long> {
    Authority findByUserId(long userId);
}
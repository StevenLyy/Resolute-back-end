package com.ly.Resolute.repository;

import com.ly.Resolute.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepo extends JpaRepository<Routine, Long> {
}
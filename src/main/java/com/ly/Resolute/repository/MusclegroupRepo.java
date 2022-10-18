package com.ly.Resolute.repository;

import com.ly.Resolute.model.Musclegroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusclegroupRepo extends JpaRepository<Musclegroup, Long> {

}
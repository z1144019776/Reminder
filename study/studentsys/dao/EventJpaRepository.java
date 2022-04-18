package com.study.studentsys.dao;

import com.study.studentsys.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJpaRepository extends JpaRepository<Event, Long> {

}

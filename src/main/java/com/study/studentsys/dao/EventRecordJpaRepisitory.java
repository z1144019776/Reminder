package com.study.studentsys.dao;

import com.study.studentsys.model.EventRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRecordJpaRepisitory extends JpaRepository<EventRecord, Long> {


    List<EventRecord> getEventRecordsByEventNameContains(String name);
}

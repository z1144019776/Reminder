package com.study.studentsys.mapper;

import com.study.studentsys.model.EventRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EventRecordMapper {

    List<EventRecord> findByEmail(@Param("email") String email);

    List<EventRecord> findByTitleEmail(@Param("title") String title,@Param("email") String email);

    List<EventRecord> findList(@Param("title") String title, @Param("type") String type, @Param("email") String email);
}

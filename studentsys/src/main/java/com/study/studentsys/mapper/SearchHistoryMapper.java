package com.study.studentsys.mapper;

import com.study.studentsys.entity.SearchHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface SearchHistoryMapper {
    List<SearchHistory> findTop5(@Param("email") String email);
    void insert(SearchHistory searchHistory);
    SearchHistory findByKeyword(@Param("keyword") String keyword, @Param("email") String email);
    void updateTimeByKeyword(@Param("keyword") String keyword, @Param("email") String email, @Param("time")Date time);
}

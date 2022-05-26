package com.study.studentsys.service;

import com.study.studentsys.entity.SearchHistory;
import com.study.studentsys.mapper.SearchHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SearchHistoryService {

    @Autowired
    private SearchHistoryMapper searchHistoryMapper;

    public List<SearchHistory> findTop5(String email) {
        return searchHistoryMapper.findTop5(email);
    }

    public void addHistory(String keyword, String email) {
        SearchHistory byKeyword = searchHistoryMapper.findByKeyword(keyword, email);
        if (byKeyword == null) {
            // 为空就插入新的
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setEmail(email);
            searchHistory.setKeyword(keyword);
            searchHistory.setSearchTime(new Date());
            searchHistoryMapper.insert(searchHistory);
        } else {
            // 不空就更新
            searchHistoryMapper.updateTimeByKeyword(keyword, email, new Date());
        }
    }

}

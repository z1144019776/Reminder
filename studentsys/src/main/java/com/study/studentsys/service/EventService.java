package com.study.studentsys.service;

import com.study.studentsys.component.CronComponent;
import com.study.studentsys.dao.EventJpaRepository;
import com.study.studentsys.dao.EventRecordJpaRepisitory;
import com.study.studentsys.mapper.EventRecordMapper;
import com.study.studentsys.model.Event;
import com.study.studentsys.model.EventRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private CronComponent cronComponent;

    @Autowired
    private EventJpaRepository eventJpaRepository;

    @Autowired
    private EventRecordJpaRepisitory eventRecordJpaRepisitory;

    @Autowired
    private EventRecordMapper eventRecordMapper;

    @Autowired
    private SearchHistoryService searchHistoryService;

    /**
     * 事件保存
     */
    public Event saveEvent(Event event) {
        return eventJpaRepository.save(event);
    }

    /**
     * 时间查询
     */
    public List<Event> getEventList() {
        return eventJpaRepository.findAll();
    }

    public void deleteEvent(Long id) {
        eventRecordJpaRepisitory.deleteById(id);
    }

    /**
     * 保存日历
     *
     * @param eventRecord
     * @return
     */
    public EventRecord saveEventRecord(EventRecord eventRecord, String email) {
        // 获取事件的开始时间
        Date targetDate = eventRecord.getStartTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(targetDate);
        // 提前一小时提醒
        calendar.add(Calendar.HOUR, -1);
        targetDate = calendar.getTime();

        // 设置定时任务发送邮件
        cronComponent.cronJob(targetDate, email, eventRecord.getEventName());
        eventRecord.setEmail(email);
        return eventRecordJpaRepisitory.save(eventRecord);
    }

    public List<EventRecord> getEventRecordListByEmail(String email) {
        return eventRecordMapper.findByEmail(email);
    }

    /**
     * 时间查询
     */
    public List<EventRecord> getEventRecordList(String title, String type, String email) {
//        if (title == null || title.equals("")) {
////            return eventRecordJpaRepisitory.findAll();
//            return eventRecordMapper.findByEmail(email);
//        } else {
////            return eventRecordJpaRepisitory.getEventRecordsByEventNameContains(title);
//            title = "%" + title + "%";
//            return eventRecordMapper.findByTitleEmail(title, email);
//        }
        // 搜索关键字不为空就记录搜索历史
        if (!StringUtils.isEmpty(title)) {
            searchHistoryService.addHistory(title, email);
        }
        return eventRecordMapper.findList(title, type, email);
    }

}

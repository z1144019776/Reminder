
package com.study.studentsys.controller;

import com.study.studentsys.entity.User;
import com.study.studentsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.studentsys.model.Event;
import com.study.studentsys.model.EventRecord;
import com.study.studentsys.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private EventService eventService;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");


    @RequestMapping("/")
    public String hello(Model model) {
        return "index";
    }

    @RequestMapping("/home")
    public String calendarHome(Model model) {
        return "calendar-home";
    }


    @RequestMapping("/list")
    public String eventList(Model mode, @RequestParam(value = "title",required = false) String title, HttpSession session) {
        String email = (String) session.getAttribute("email");
        List<EventRecord> eventList = eventService.getEventRecordList(title, email);
        mode.addAttribute("list", eventList);
        mode.addAttribute("title", title);
        return "calendar-list";
    }

    @RequestMapping("/deleteEvent/{id}")
    private String deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return "redirect:/home";
    }


    @RequestMapping("/selectEventRecord")
    @ResponseBody
    public List<EventRecord> getEventList(HttpSession session) {
        String email = (String) session.getAttribute("email");
        return eventService.getEventRecordList(null, email);
    }

    @RequestMapping("/saveEventRecord")
    @ResponseBody
    public Long saveEventRecord(@RequestParam(name = "eventName") String eventName,
                                @RequestParam(name = "startTime") String startTime,
                                @RequestParam(name = "endTime") String endTime,
                                HttpSession session) {
        // 从session中获取邮箱，需要和登录结合使用
        String email = (String) session.getAttribute("email");
        if (StringUtils.isEmpty(email)) {
            // 为了测试这里暂时可以是你自己的邮箱
            email = "1351566457@qq.com";
        }

        EventRecord eventRecord = new EventRecord();
        eventRecord.setEventName(eventName);
        try {
            eventRecord.setStartTime(dateFormat.parse(startTime));
            eventRecord.setEndTime(dateFormat.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        EventRecord savedevent = eventService.saveEventRecord(eventRecord, email);
        return savedevent.getId();
    }

    // 自动注入userService
    @Autowired
    private UserService userService;

    @GetMapping("/detail.html")
    public String detail() {
        return "detail";
    }

    @GetMapping("/reset.html")
    public String resetPwd() {
        return "reset";
    }

    @GetMapping("/getAllUser")
    @ResponseBody //这个注解是让返回值以 json 的格式返回
    public List<User> getAllUser() {
        return userService.findAll();
    }

}

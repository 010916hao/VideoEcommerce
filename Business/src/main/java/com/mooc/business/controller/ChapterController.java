package com.mooc.business.controller;

import com.mooc.server.domain.Chapter;
import com.mooc.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/chapter")
    public List<Chapter> chapter() {
        return chapterService.list();
    }
}

package com.mooc.business.controller.admin;

import com.mooc.server.domain.Chapter;
import com.mooc.server.dto.ChapterDto;
import com.mooc.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/list")
    public List<ChapterDto> chapter() {
        return chapterService.list();
    }
}

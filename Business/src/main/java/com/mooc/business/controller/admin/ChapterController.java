package com.mooc.business.controller.admin;


import com.mooc.server.dto.PageDto;
import com.mooc.server.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);

    @Resource
    private ChapterService chapterService;

    //we need @RequestBody annotation to tell controller that the information is passing through by JSON
    @RequestMapping("/list")
    public PageDto chapter(@RequestBody PageDto pageDto) {
        LOG.info("PageDto: {}", pageDto);
        chapterService.list(pageDto);
        return pageDto;
    }
}

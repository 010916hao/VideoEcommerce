package com.mooc.business.controller.admin;


import com.mooc.server.dto.ChapterDto;
import com.mooc.server.dto.PageDto;
import com.mooc.server.dto.ResponseDto;
import com.mooc.server.service.ChapterService;
import com.mooc.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);
    public static final String BUSINESS_NAME = "Chapter";

    @Resource
    private ChapterService chapterService;

    //we need @RequestBody annotation to tell controller that the information is passing through by JSON
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        LOG.info("PageDto: {}", pageDto);
        ResponseDto responseDto = new ResponseDto();
        chapterService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto) {
        LOG.info("ChapterDto: {}", chapterDto);

        //Validation for length and Null
        ValidatorUtil.require(chapterDto.getCourseId(), "course ID");
        ValidatorUtil.require(chapterDto.getName(), "Name");
        ValidatorUtil.length(chapterDto.getCourseId(), "course ID", 1, 8);

        ResponseDto responseDto = new ResponseDto();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        LOG.info("id: {}", id);
        ResponseDto responseDto = new ResponseDto();
        chapterService.delete(id);
        return responseDto;
    }
}

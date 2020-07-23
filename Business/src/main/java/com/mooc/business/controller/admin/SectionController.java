package com.mooc.business.controller.admin;


import com.mooc.server.dto.SectionDto;
import com.mooc.server.dto.PageDto;
import com.mooc.server.dto.ResponseDto;
import com.mooc.server.service.SectionService;
import com.mooc.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/section")
public class SectionController {

    private static final Logger LOG = LoggerFactory.getLogger(SectionController.class);
    public static final String BUSINESS_NAME = "Section";

    @Resource
    private SectionService sectionService;

    //we need @RequestBody annotation to tell controller that the information is passing through by JSON
    /**
    * List Query
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        LOG.info("PageDto: {}", pageDto);
        ResponseDto responseDto = new ResponseDto();
        sectionService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * Save
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SectionDto sectionDto) {
        LOG.info("SectionDto: {}", sectionDto);

        //Save validation for length and Null
        ValidatorUtil.require(sectionDto.getTitle(), "title");
        ValidatorUtil.length(sectionDto.getTitle(), "title", 1, 50);
        System.out.println("video validation");
        ValidatorUtil.length(sectionDto.getVideo(), "video", 1, 200);
        ResponseDto responseDto = new ResponseDto();
        sectionService.save(sectionDto);
        responseDto.setContent(sectionDto);
        return responseDto;
    }

    /**
    * Delete
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        LOG.info("id: {}", id);
        ResponseDto responseDto = new ResponseDto();
        sectionService.delete(id);
        return responseDto;
    }
}
package com.mooc.${module}.controller.admin;


import com.mooc.server.dto.${Domain}Dto;
import com.mooc.server.dto.PageDto;
import com.mooc.server.dto.ResponseDto;
import com.mooc.server.service.${Domain}Service;
import com.mooc.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/${domain}")
public class ${Domain}Controller {

    private static final Logger LOG = LoggerFactory.getLogger(${Domain}Controller.class);
    public static final String BUSINESS_NAME = "${Domain}";

    @Resource
    private ${Domain}Service ${domain}Service;

    //we need @RequestBody annotation to tell controller that the information is passing through by JSON
    /**
    * List Query
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        LOG.info("PageDto: {}", pageDto);
        ResponseDto responseDto = new ResponseDto();
        ${domain}Service.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * Save
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody ${Domain}Dto ${domain}Dto) {
        LOG.info("${Domain}Dto: {}", ${domain}Dto);

        //Save validation for length and Null

        ResponseDto responseDto = new ResponseDto();
        ${domain}Service.save(${domain}Dto);
        responseDto.setContent(${domain}Dto);
        return responseDto;
    }

    /**
    * Delete
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        LOG.info("id: {}", id);
        ResponseDto responseDto = new ResponseDto();
        ${domain}Service.delete(id);
        return responseDto;
    }
}
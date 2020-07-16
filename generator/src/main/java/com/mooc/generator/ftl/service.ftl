package com.mooc.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.mooc.server.domain.${Domain};
import com.mooc.server.domain.${Domain}Example;
import com.mooc.server.dto.${Domain}Dto;
import com.mooc.server.dto.PageDto;
import com.mooc.server.mapper.${Domain}Mapper;
import com.mooc.server.util.CopyUtil;
import com.mooc.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ${Domain}Service {

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    /**
    * List Query
    */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);
        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        pageDto.setTotal(pageInfo.getTotal());

        List<${Domain}Dto> ${domain}DtoList = CopyUtil.copyList(${domain}List, ${Domain}Dto.class);
        pageDto.setList(${domain}DtoList);
    }


    /**
    * Save，when it has id, update, if not, add
    */
    public void save(${Domain}Dto ${domain}Dto) {
        ${Domain} ${domain} = CopyUtil.copy(${domain}Dto, ${Domain}.class);
        if (StringUtil.isEmpty(${domain}Dto.getId()))
            insert(${domain});
        else
            update(${domain});
    }


    /**
    * Add
    */
    private void insert(${Domain} ${domain}) {
        ${domain}.setId(UuidUtil.getShortUuid());
        ${domain}Mapper.insert(${domain});
    }

    /**
    * update
    */
    private void update(${Domain} ${domain}) {
        ${domain}Mapper.updateByPrimaryKey(${domain});
    }


    /**
    * Delete
    */
    public void delete(String id) {
        ${domain}Mapper.deleteByPrimaryKey(id);
    }




}

package com.mooc.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.mooc.server.domain.Section;
import com.mooc.server.domain.SectionExample;
import com.mooc.server.dto.SectionDto;
import com.mooc.server.dto.PageDto;
import com.mooc.server.mapper.SectionMapper;
import com.mooc.server.util.CopyUtil;
import com.mooc.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Date;
@Service
public class SectionService {

    @Resource
    private SectionMapper sectionMapper;

    /**
    * List Query
    */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SectionExample sectionExample = new SectionExample();
        sectionExample.setOrderByClause("sort asc");
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        pageDto.setTotal(pageInfo.getTotal());

        List<SectionDto> sectionDtoList = CopyUtil.copyList(sectionList, SectionDto.class);
        pageDto.setList(sectionDtoList);
    }


    /**
    * Save，when it has id, update, if not, add
    */
    public void save(SectionDto sectionDto) {
        Section section = CopyUtil.copy(sectionDto, Section.class);
        if (StringUtil.isEmpty(sectionDto.getId()))
            insert(section);
        else
            update(section);
    }


    /**
    * Add
    */
    private void insert(Section section) {
        Date now = new Date();
        section.setCreatedAt(now);
        section.setUpdatedAt(now);
        section.setId(UuidUtil.getShortUuid());
        sectionMapper.insert(section);
    }

    /**
    * update
    */
    private void update(Section section) {
        section.setUpdatedAt(new Date());
        sectionMapper.updateByPrimaryKey(section);
    }


    /**
    * Delete
    */
    public void delete(String id) {
        sectionMapper.deleteByPrimaryKey(id);
    }




}

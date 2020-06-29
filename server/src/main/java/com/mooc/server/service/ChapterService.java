package com.mooc.server.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mooc.server.domain.Chapter;
import com.mooc.server.domain.ChapterExample;
import com.mooc.server.dto.ChapterDto;
import com.mooc.server.dto.PageDto;
import com.mooc.server.mapper.ChapterMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;


    public void list(PageDto pageDto) {
        //call startPage method means when the first following
        //select sql is executed, the results will be paginated.
        //PageHelper.startPage(1, 2);
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ChapterExample chapterExample = new ChapterExample();
        //chapterExample.createCriteria().andIdEqualTo("1");
        //chapterExample.setOrderByClause("id asc");
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        List<ChapterDto> chapterDtoList = new ArrayList<>();
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());
        //pageDto.setTotal(chapterList.size());
        for (int i = 0, l = chapterList.size(); i < l; i++){
            Chapter chapter = chapterList.get(i);
            ChapterDto chapterDto = new ChapterDto();
            //BeanUtils is a spring utility class, which is used for the copy of entities.
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        pageDto.setList(chapterDtoList);

    }
}

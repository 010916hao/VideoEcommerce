package com.mooc.server.service;

import com.mooc.server.domain.Chapter;
import com.mooc.server.domain.ChapterExample;
import com.mooc.server.mapper.ChapterMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public List<Chapter> list() {
        ChapterExample chapterExample = new ChapterExample();
        //chapterExample.createCriteria().andIdEqualTo("1");
        chapterExample.setOrderByClause("id asc");
        return chapterMapper.selectByExample(chapterExample);
    }
}

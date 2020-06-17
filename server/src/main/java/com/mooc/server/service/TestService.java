package com.mooc.server.service;

import com.mooc.server.domain.Test;
import com.mooc.server.domain.TestExample;
import com.mooc.server.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        TestExample testExample = new TestExample();
        //testExample.createCriteria().andIdEqualTo("1");
        testExample.setOrderByClause("id asc");
        return testMapper.selectByExample(testExample);
    }
}

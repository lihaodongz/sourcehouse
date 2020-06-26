package com.ironman.sourcehouse.dao;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.ironman.SpringBootSeedApplicationTests;
import com.ironman.sourcehouse.model.SupportAddress;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 19:42
 **/

public class SupportAddressMapperTest extends SpringBootSeedApplicationTests {

    Logger logger = LoggerFactory.getLogger(SupportAddressMapperTest.class);

    @Autowired
     private SupportAddressMapper supportAddressMapper;

    @Test
    public void testGetall(){
        PageHelper.startPage(1,2);
        List<SupportAddress> all =
                supportAddressMapper.getByLevel("city");
        logger.info(JSON.toJSONString(all,true));
    }
}

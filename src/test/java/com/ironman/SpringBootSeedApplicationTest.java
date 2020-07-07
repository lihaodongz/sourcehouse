package com.ironman;

import com.ironman.common.configuration.ElacticsearchTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lihaodong
 * @create 2020/7/7 1:15 下午
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSeedApplicationTest {



    @Autowired
    private ElacticsearchTemplate elacticsearchTemplate;

    @Test
    public void testEs(){
        Boolean test = elacticsearchTemplate.createIndex("testliahodong111");
        System.out.println(test);
    }
}

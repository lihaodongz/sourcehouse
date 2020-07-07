package com.ironman.sourcehouse.service.Imp;

import com.ironman.sourcehouse.dao.HouseTagMapper;
import com.ironman.sourcehouse.model.HouseTag;
import com.ironman.sourcehouse.service.ITagSerivce;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lihaodong
 * @create 2020/7/1 7:39 下午
 */
@Service
public class TagSerivceImpl implements ITagSerivce {


    @Resource
    private HouseTagMapper houseTagMapper;


    @Override
    public List<String> getTag(Long houseId) {
        List<String> collect = Collections.emptyList();
        Optional<List<HouseTag>> houseTags = Optional.of(houseTagMapper.fetchTags(houseId.intValue()));
        if (houseTags.isPresent()){
           collect = houseTags.get().stream().map(HouseTag::getName).collect(Collectors.toList());
        }
        return collect;
    }
}

package com.ironman.sourcehouse.service.Imp;

import com.ironman.common.base.BaseResult;
import com.ironman.common.base.BaseResultGenerator;
import com.ironman.common.req.UserReq;
import com.ironman.sourcehouse.dao.UserMapper;
import com.ironman.sourcehouse.model.User;
import com.ironman.sourcehouse.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 17:33
 **/
@Service
public class UserServiceImpl implements IUserService {


    @Resource
    private UserMapper userMapper;


    @Override
    public BaseResult login(UserReq req) {
        String username = req.getUsername();
        User user = new User();
        user.setName(username);
        List<User> users = userMapper.selectUser(user);
        if (users.size() > 1 || Objects.isNull(users) || users.size() == 0) {
            return BaseResultGenerator.failure();
        }
        User user1 = users.get(0);
        if (user1.getPassword().equals(req.getPassword())){
            return BaseResultGenerator.success();
        }else{
            return BaseResultGenerator.failure();
        }
    }
}

package com.ironman.sourcehouse.service;

import com.ironman.common.base.BaseResult;
import com.ironman.common.req.UserReq;

/**
 * 用户 Service
 *
 * @author zhaozhenyao
 * @date 2018/5/8
 */
public interface IUserService {


    BaseResult login(UserReq req);

}

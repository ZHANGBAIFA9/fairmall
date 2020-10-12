package com.fairmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fairmall.entity.po.Users;

/**
 * @类说明 【用户表】业务逻辑接口
 * @author ZHANGBAIFA
 * @date 2020-10-10 11:46:58
 **/
public interface IUsersService extends IService<Users> {
	
	/**
     * 获取用户表
     *
     * @param id
     * @return
     */
	Users get(String id);

    /**
     * 新增用户表
     *
     * @param users
     * @return
     */
    boolean add(Users users);

    /**
     * 更新资源信息
     *
     * @param users
     */
    boolean update(Users users);

    /**
     * 根据id删除资源
     *
     * @param id
     */
    boolean delete(String id);

}

package com.fairmall.dao;

 
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fairmall.entity.po.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * @类说明 【用户表】数据访问层
 * @author ZHANGBAIFA
 * @date 2020-10-10 11:46:58
 **/
@Mapper
@Repository
public interface UsersMapper extends BaseMapper<Users> {
	
//	/**
//	 * @方法说明 自义联表查询
//	 **/
//	IPage<Users> queryPage(IPage<Users> page, Users param);
}

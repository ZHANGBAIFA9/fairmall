package com.fairmall.service.impl;


import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fairmall.dao.UsersMapper;
import com.fairmall.entity.po.Users;
import com.fairmall.service.IUsersService;
import org.springframework.stereotype.Service;

/**
 * @类说明 【用户表】业务逻辑实现
 * @author ZHANGBAIFA
 * @date 2020-10-10 11:46:58
 **/
//@Slf4j
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
//	@Autowired
//	private IUsersMapper usersMapper;//注入【用户表】数据访问接口
//	
//	/**
//	 * @方法说明 自定义联表查询
//	 **/
//	@Override
//	public IPage<Users> queryPage(IPage<Users> page, Users param){
//		return usersMapper.queryPage(page,param);
//	}
	@Override
    public boolean add(Users users) {
        return this.save(users);
    }

    @Override
    @Cached(name = "users::", key = "#id", cacheType = CacheType.BOTH)
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    @Cached(name = "users::", key = "#users.id", cacheType = CacheType.BOTH)
    public boolean update(Users users) {
        return this.updateById(users);
    }

    @Override
    @Cached(name = "users::", key = "#id", cacheType = CacheType.BOTH)
    public Users get(String id) {
        return this.getById(id);
    }
	
}
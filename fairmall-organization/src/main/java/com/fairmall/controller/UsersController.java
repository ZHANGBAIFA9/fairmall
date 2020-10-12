package com.fairmall.controller;

import javax.validation.Valid;

import com.fairmall.entity.form.UsersForm;
import com.fairmall.entity.po.Users;
import com.fairmall.entity.vo.Result;
import com.fairmall.service.IUsersService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @类说明 【用户表】控制器
 * @author ZHANGBAIFA
 * @date 2020-10-10 11:46:58
 **/
@Api(tags = "用户表接口")
@RestController
@RequestMapping("users")
public class UsersController {

	@Autowired
	private IUsersService usersService;//注入【用户表】业务逻辑接口
	
	/**
	 * @方法说明  新增【用户表】记录
	 */
	@PostMapping("add")
	@ApiOperation(value = "新增【用户表】记录")
	@ApiImplicitParam(name = "usersForm", value = "新增【用户表】form表单", required = true, dataType = "{upp}Form")
	 public Result<Boolean> add(@RequestBody @Valid UsersForm usersForm) {
 		return Result.success(usersService.add(usersForm.toPo(Users.class)));
	}
	
	/**
	 * @方法说明 按主键删除【用户表】记录
	 */
	@ApiOperation(value = "按主键删除【用户表】记录")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户表ID", required = true, dataType = "string")
	@DeleteMapping(value = "/{id}")
	public Result<Boolean> delete(@PathVariable String id) {
		return Result.success(usersService.delete(id));
	}
	
	/**
	 * @方法说明 修改【用户表】记录
	 */
	
	@ApiOperation(value = "修改【用户表】记录")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "用户表ID", required = true, dataType = "string"),
        @ApiImplicitParam(name = "usersForm", value = "用户表实体", required = true, dataType = "{upp}Form")
	})
	@PutMapping(value = "/{id}")
	public Result<Boolean> update(@PathVariable String id, @Valid @RequestBody UsersForm usersForm) {
//		User user = userUpdateForm.toPo(User.class);
//		user.setId(id);
//		return Result.success(userService.update(user));
		return null ;
	}
	
	
	
	/**
	 * @方法说明 按主键查单个【用户表】记录
	 */
	@ApiOperation(value = "按主键查单个【用户表】记录")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户表ID", required = true, dataType = "string")
	@GetMapping(value = "/{id}")
	public Result<Users> get(String id) {
		return Result.success(usersService.get(id));
	}
}

package com.fairmall.entity.form;


import com.fairmall.entity.po.Users;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @类说明 【用户表】DTO对象
 * @author ZHANGBAIFA
 * @date 2020-10-10 11:46:58
 **/
@ApiModel
@Data
public class UsersForm extends BaseForm<Users>  {
	/* -用户名 */
	@ApiModelProperty(value = "Users")
	private String username;
	/* -用户密码密文 */
	@ApiModelProperty(value = "Users")
	private String password;
	/* -用户姓名 */
	@ApiModelProperty(value = "Users")
	private String name;
	/* -用户手机 */
	@ApiModelProperty(value = "Users")
	private String mobile;
	/* -简介 */
	@ApiModelProperty(value = "Users")
	private String description;
	/* -是否已删除Y：已删除，N：未删除 */
	@ApiModelProperty(value = "Users")
	private String deleted;
	/* -是否有效用户 */
	@ApiModelProperty(value = "Users")
	private Byte enabled;
	/* -账号是否未过期 */
	@ApiModelProperty(value = "Users")
	private Byte account_non_expired;
	/* -密码是否未过期 */
	@ApiModelProperty(value = "Users")
	private Byte credentials_non_expired;
	/* -是否未锁定 */
	@ApiModelProperty(value = "Users")
	private Byte account_non_locked;
}
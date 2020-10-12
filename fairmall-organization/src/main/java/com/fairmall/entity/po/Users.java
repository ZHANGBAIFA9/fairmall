package com.fairmall.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.*;

/**
 * @类说明 【用户表】实体类
 * @author ZHANGBAIFA
 * @date 2020-10-10 11:46:58
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "users")
public class Users extends BasePo {
	private static final long serialVersionUID = 1L;
//	@NotNull(message = "客户主建(Id)不能为空(数值型)")
//	@NotEmpty(message = "客户名称(name)不能为空(字符型)")
//	@Length(max = 20, min = 10, message = "客户名称(name)长度在有10~20之间(字符型)")
//	@Max(value = 1000, message = "客户年龄(age)最大值是1000(数值型)")
//	@Min(value = 100, message = "客户年龄(age)最小值是100(数值型)")
//	@Pattern(regexp = "^\\d{10}$", message = "必须为10位数字(字符型)")
	/* -用户名 */
	private String username; 
	/* -用户密码密文 */
	private String password; 
	/* -用户姓名 */
	private String name; 
	/* -用户手机 */
	private String mobile; 
	/* -简介 */
	private String description; 
	/* -是否已删除Y：已删除，N：未删除 */
	private String deleted; 
	/* -是否有效用户 */
	private Byte enabled; 
	/* -账号是否未过期 */
	private Byte account_non_expired; 
	/* -密码是否未过期 */
	private Byte credentials_non_expired; 
	/* -是否未锁定 */
	private Byte account_non_locked; 
}
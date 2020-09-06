package com.apple.product.entity;

import com.apple.common.valid.AddGroup;
import com.apple.common.valid.ListVal;
import com.apple.common.valid.UpdateGroup;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 * 
 * @author fenx
 * @email fengx@gmail.com
 * @date 2020-09-01 21:11:24
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@NotNull(message = "修改id不能为空", groups = UpdateGroup.class)
	@Null(message = "添加不能携带id", groups = AddGroup.class)
	@TableId
	private Long brandId;
	/**
	 * $column.comments
	 * @NotBlank 校验器
	 */
	@NotBlank(message = "品牌名不能为空", groups = { AddGroup.class, UpdateGroup.class })
	private String name;
	/**
	 * $column.comments
	 * 加了分组，其他的未加分组的校验注解就失效了
	 */
	@URL(message = "logo图片必须是一个合法的url地址")
	private String logo;
	/**
	 * $column.comments
	 */
	private String descript;
	/**
	 * $column.comments
	 * 只能是0或者1，如何校验？
	 * 自己编写一个校验注解和校验器
	 */
	@ListVal(values = { 0, 1 }, groups = AddGroup.class)
	private Integer showStatus;
	/**
	 * $column.comments
	 * @Pattern: 自定义校验规则，正则表达式
	 */
	@Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是字母")
	private String firstLetter;
	/**
	 * $column.comments
	 */
	@Min(value = 0, message = "排序必须大于等于0")
	private Integer sort;

}

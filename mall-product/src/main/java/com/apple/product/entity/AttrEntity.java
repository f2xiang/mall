package com.apple.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * 商品属性
 * 
 * @author fenx
 * @email fengx@gmail.com
 * @date 2020-09-01 21:11:23
 */
@Data
@TableName("pms_attr")
@ToString
public class AttrEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId
	private Long attrId;
	/**
	 * $column.comments
	 */
	private String attrName;
	/**
	 * $column.comments
	 */
	private Integer searchType;
	/**
	 * $column.comments
	 */
	private String icon;
	/**
	 * $column.comments
	 */
	private String valueSelect;
	/**
	 * $column.comments
	 */
	private Integer attrType;
	/**
	 * $column.comments
	 */
	private Long enable;
	/**
	 * $column.comments
	 */
	private Long catelogId;
	/**
	 * $column.comments
	 */
	private Integer showDesc;

}

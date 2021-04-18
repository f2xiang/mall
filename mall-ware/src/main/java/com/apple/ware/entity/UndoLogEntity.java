package com.apple.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author fengx
 * @email fengx@gmail.com
 * @date 2020-09-02 21:39:41
 */
@Data
@TableName("undo_log")
public class UndoLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId
	private Long id;
	/**
	 * $column.comments
	 */
	private Long branchId;
	/**
	 * $column.comments
	 */
	private String xid;
	/**
	 * $column.comments
	 */
	private String context;
	/**
	 * $column.comments
	 */
	private byte rollbackInfo;
	/**
	 * $column.comments
	 */
	private Integer logStatus;
	/**
	 * $column.comments
	 */
	private Date logCreated;
	/**
	 * $column.comments
	 */
	private Date logModified;
	/**
	 * $column.comments
	 */
	private String ext;

}

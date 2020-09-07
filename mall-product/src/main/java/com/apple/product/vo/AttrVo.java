package com.apple.product.vo;


import lombok.Data;

/**
 * @description: 属性提交
 * @author: fengx
 * @create: 2020-09-07 20:23
 */
@Data
public class AttrVo {

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


    private Long attrGroupId;

    private Long valueType;

}

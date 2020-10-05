package com.apple.product.vo;

import com.apple.product.entity.AttrEntity;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: fengx
 * @create: 2020-10-05 21:02
 */
@Data
public class AttrGroupWithAttrVo {

    private Long attrGroupId;
    /**
     * $column.comments
     */
    private String attrGroupName;
    /**
     * $column.comments
     */
    private Integer sort;
    /**
     * $column.comments
     */
    private String descript;
    /**
     * $column.comments
     */
    private String icon;
    /**
     * $column.comments
     */
    private Long catelogId;

    private List<AttrEntity> attrs;
}

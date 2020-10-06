package com.apple.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: fengx
 * @create: 2020-10-06 15:55
 */
@Data
public class SkuFullReductionTo {

    private long skuId;

    private int fullCount; // 满几件
    private BigDecimal discount; // 打折
    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private int priceStatus;
    private List<String> memberPrice;

}

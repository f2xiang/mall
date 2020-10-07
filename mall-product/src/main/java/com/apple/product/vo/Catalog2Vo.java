package com.apple.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @author: fengx
 * @create: 2020-10-06 22:24
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catalog2Vo {

    private long catalog1Id;
    private long id;
    private String name;
    private List<Catalog3Vo> catalog3List;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Catalog3Vo {
        private long catalog2Id;
        private long id;
        private String name;

    }
}

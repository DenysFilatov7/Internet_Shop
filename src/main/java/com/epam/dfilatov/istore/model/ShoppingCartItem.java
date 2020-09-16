package com.epam.dfilatov.istore.model;

import java.io.Serializable;

public class ShoppingCartItem implements Serializable {
    private static final long serialVersionUID = 2217540930639347309L;
    private int productId;
    private int count;

    public ShoppingCartItem() {
        super();
    }

    public ShoppingCartItem(int productId, int count) {
        super();
        this.productId = productId;
        this.count = count;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "productId=" + productId +
                ", count=" + count +
                '}';
    }
}

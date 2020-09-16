package com.epam.dfilatov.istore.model;

import com.epam.dfilatov.istore.constants.Constants;
import com.epam.dfilatov.istore.exception.ValidationException;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = -1782798047762294647L;
    private Map<Integer, ShoppingCartItem> products = new HashMap<>();
    private int totalCount = 0;

    public void addProduct(int productId, int count) throws ValidationException {
        validateShoppingCartSize(productId);
        ShoppingCartItem shoppingCartItem = products.get(productId);
        if (shoppingCartItem == null) {
            validateProductCount(count);
            shoppingCartItem = new ShoppingCartItem(productId, count);
            products.put(productId, shoppingCartItem);
        } else {
            validateProductCount(count + shoppingCartItem.getCount());
            shoppingCartItem.setCount(shoppingCartItem.getCount() + count);
        }
        refreshStatistics();
    }

    public void removeProduct(int productId, int count){
        ShoppingCartItem shoppingCartItem = products.get(productId);
        if(shoppingCartItem != null){
            if(shoppingCartItem.getCount() > count){
                shoppingCartItem.setCount(shoppingCartItem.getCount() - count);
            }else {
                products.remove(productId);
            }
            refreshStatistics();
        }
    }
    
    private void refreshStatistics() {
        totalCount = 0;
        for (ShoppingCartItem shoppingCartItem : getItems()) {
            totalCount += shoppingCartItem.getCount();
        }
    }

    public Collection<ShoppingCartItem> getItems() {
        return products.values();
    }

    public int getTotalCount() {
        return totalCount;
    }

    private void validateProductCount(int count) {
        if (count > Constants.MAX_PRODUCT_COUNT_PER_SHOPPING_CART) {
            throw new ValidationException("Limit for product count reached: size = " + count);
        }
    }

    private void validateShoppingCartSize(int productId) throws ValidationException {
        if (products.size() > Constants.MAX_PRODUCT_PER_SHOPPING_CART
                || (products.size() == Constants.MAX_PRODUCT_PER_SHOPPING_CART && !products.containsKey(productId))) {
            throw new ValidationException("Limit for Shopping cart size reached: size = " + products.size());
        }
    }

    public String getView() {
        StringBuilder stringBuilder = new StringBuilder();
        for(ShoppingCartItem item : getItems()){
            stringBuilder.append(item.getProductId())
                    .append("-&gt")
                    .append(item.getCount())
                    .append("<br>");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "products=" + products +
                ", totalCount=" + totalCount +
                '}';
    }
}

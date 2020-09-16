package com.epam.dfilatov.istore.filter;

import com.epam.dfilatov.istore.model.ShoppingCart;
import com.epam.dfilatov.istore.model.ShoppingCartItem;
import com.epam.dfilatov.istore.util.SessionUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/")
public class AutoRestoreShoppingCartFilter implements Filter {

    private static final String SHOPPING_CARD_DESERIALIZATION_DONE = "SHOPPING_CARD_DESERIALIZATION_DONE";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getSession().getAttribute(SHOPPING_CARD_DESERIALIZATION_DONE) == null) {
            if (!SessionUtils.isCurrentShoppingCartCreated(req)) {
                Cookie cookie = SessionUtils.findShoppingCartCookie(req);
                if (cookie != null) {
                    ShoppingCart shoppingCart = shoppingCartFromString(cookie.getValue());
                    SessionUtils.setCurrentShoppingCart(req, shoppingCart);
                }
            }
            req.getSession().setAttribute(SHOPPING_CARD_DESERIALIZATION_DONE, Boolean.TRUE);
        }
        filterChain.doFilter(req, resp);
    }

    protected ShoppingCart shoppingCartFromString(String value) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] items = value.split("\\|");
        for (String item : items) {
            String[] data = item.split("-");
            try {
                shoppingCart.addProduct(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return shoppingCart;
    }

    protected String shoppingCartToString(ShoppingCart shoppingCart) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ShoppingCartItem item : shoppingCart.getItems()) {
            stringBuilder.append(item.getProductId())
                    .append("-")
                    .append(item.getCount())
                    .append("|");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    @Override
    public void destroy() {
    }
}

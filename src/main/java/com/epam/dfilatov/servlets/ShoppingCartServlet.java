package com.epam.dfilatov.servlets;

import com.epam.dfilatov.model.ShoppingCart;
import com.epam.dfilatov.model.ShoppingCartItem;
import com.epam.dfilatov.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/current-cart")
public class ShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd = req.getParameter("cmd");
        if ("clear".equals(cmd)) {
            SessionUtils.clearCurrentShoppingCart(req, resp);
        } else if ("invalidate".equals(cmd)) {
            req.getSession().invalidate();
        } else if ("add".equals(cmd)) {
            addProduct(req, resp);
        } else {
            sync(req, resp);
        }
        showShoppingCart(req, resp);
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) {
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        Random random = new Random();
        shoppingCart.addProduct(random.nextInt(2), random.nextInt(1) + 1);
    }

    private void sync(HttpServletRequest req, HttpServletResponse resp) {
        if (!SessionUtils.isCurrentShoppingCartCreated(req)) {
            Cookie cookie = SessionUtils.findShoppingCartCookie(req);
            if (cookie != null) {
                ShoppingCart shoppingCart = shoppingCartFromString(cookie.getValue());
                SessionUtils.setCurrentShoppingCart(req, shoppingCart);
            }
        } else {
            ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
            String cookieValue = shoppingCartToString(shoppingCart);
            SessionUtils.updateCurrentShoppingCartCookie(cookieValue, resp);
        }
    }

    private void showShoppingCart(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
            req.getRequestDispatcher("/WEB-INF/shopping-cart.jsp").forward(req, resp);
    }

    protected String shoppingCartToString(ShoppingCart shoppingCart) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ShoppingCartItem shoppingCartItem : shoppingCart.getItems()) {
            stringBuilder.append(shoppingCartItem.getProductId())
                    .append("-")
                    .append(shoppingCartItem.getCount())
                    .append("|");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    protected ShoppingCart shoppingCartFromString(String cookieValue) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] items = cookieValue.split("\\|");
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

}

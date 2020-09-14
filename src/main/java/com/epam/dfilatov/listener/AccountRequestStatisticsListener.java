package com.epam.dfilatov.listener;

import com.epam.dfilatov.constants.Constants;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebListener
@SuppressWarnings("unchecked")
public class AccountRequestStatisticsListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = ((HttpServletRequest) servletRequestEvent.getServletRequest());
        List<String> actions = (List<String>) request.getSession().getAttribute(Constants.ACCOUNT_ACTIONS_HISTORY);
        if (actions == null) {
            actions = new ArrayList<>();
            request.getSession().setAttribute(Constants.ACCOUNT_ACTIONS_HISTORY, actions);
        }
        actions.add(getCurrentAction(request));
    }

    private String getCurrentAction(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder(request.getMethod())
                .append(" ").append(request.getRequestURI());
        Map<String, String[]> map = request.getParameterMap();
        if (map != null) {
            boolean first = true;
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                if (first) {
                    stringBuilder.append('?');
                    first = false;
                } else {
                    stringBuilder.append('&');
                }
                for (String value : entry.getValue()) {
                    stringBuilder.append(entry.getKey()).append('=').append(value).append('&');
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        }
        return stringBuilder.toString();
    }
}

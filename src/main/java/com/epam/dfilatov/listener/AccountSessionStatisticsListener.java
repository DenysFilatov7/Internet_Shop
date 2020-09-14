package com.epam.dfilatov.listener;

import com.epam.dfilatov.constants.Constants;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

@WebListener
@SuppressWarnings("unchecked")
public class AccountSessionStatisticsListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        List<String> actions = (List<String>) httpSessionEvent.getSession().getAttribute(Constants.ACCOUNT_ACTIONS_HISTORY);
        if(actions != null){
            logCurrentActionHistory(httpSessionEvent.getSession().getId(), actions);
        }
    }

    private void logCurrentActionHistory(String id, List<String> actions) {
        System.out.println(id + " ->\n\t" + String.join("\n\t", actions));
    }
}

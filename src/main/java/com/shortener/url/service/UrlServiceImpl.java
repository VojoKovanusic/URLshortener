package com.shortener.url.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortener.url.model.Url;
import com.shortener.url.model.User;
import com.shortener.url.util.Util;

@Slf4j
@Service
public class UrlServiceImpl implements UrlService {

    private UserService userService;
    private Util util;
    @Autowired

    public UrlServiceImpl(UserService userService,Util util) {
        this.userService = userService;
        this.util=util;
    }

    @Override
    public String createShortUrl(Url url) {

        String randomCharForUrl = this.util.generateString(5);

        User user = this.userService.getCurrentUser();

        url.setShortUrl("http://localhost:8090/vojo.com/" + randomCharForUrl);

         user.getMyUrlList().put(randomCharForUrl, url);

        log.info("{} MyUrlList ", user.getMyUrlList());

        return url.getShortUrl();
    }

    @Override
    public long setNumberOfVisitsForThisUrl(String shortUrlPath) {

        User user;

        try {

            user = this.userService.getCurrentUser();

            long visits = user.getMyUrlList().get(shortUrlPath).getNumberOfVisits();

            user.getMyUrlList().get(shortUrlPath)
                    .setNumberOfVisits(visits + 1);

            return user.getMyUrlList()
                    .get(shortUrlPath)
                    .getNumberOfVisits();

        } catch (NullPointerException ignored) {

        }
        return 0;
    }

    @Override
    public boolean is301RedirectType(String shortUrlPath, User user) {
        return user.getMyUrlList().
                get(shortUrlPath).
                getRedirectType() == 301;
    }
}

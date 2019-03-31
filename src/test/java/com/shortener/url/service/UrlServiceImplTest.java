package com.shortener.url.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import com.shortener.url.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.shortener.url.model.Url;
import com.shortener.url.model.User;

@RunWith(MockitoJUnitRunner.class)
class UrlServiceImplTest {

    @InjectMocks
    UrlServiceImpl urlService;

    @Mock
    UserServiceImpl userService;
    @Mock
    Util util;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testSetNumberOfVisits() {

        Url url = getUrl();

        Map<String, Url> myUrlList = new HashMap<>();

        myUrlList.put(url.getShortUrl(), url);

        User user = getUser(myUrlList);

        when(userService.getCurrentUser()).thenReturn(user);

        assertEquals(1, urlService.setNumberOfVisitsForThisUrl("http://localhost:8090/vojo.com/short"));
    }


    private User getUser(Map<String, Url> myUrlList) {

        User user = new User();
        user.setAccountId("vojo");
        user.setMyUrlList(myUrlList);

        return user;
    }


    private Url getUrl() {
        Url url = new Url();
        url.setRealUrl("real url....");
        url.setShortUrl("http://localhost:8090/vojo.com/short");
        return url;
    }


    @Test
    void testCreateShortUrl() {
        Url url = getUrl();

        Map<String, Url> myUrlList = new HashMap<>();
        myUrlList.put(url.getShortUrl(), url);


        User user = getUser(myUrlList);
        when(util.generateString(5)).thenReturn("short");

        when(userService.getCurrentUser()).thenReturn(user);

        assertEquals(url.getShortUrl(), urlService.createShortUrl(url));
    }

}
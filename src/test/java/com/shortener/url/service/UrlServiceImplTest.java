package com.shortener.url.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.shortener.url.model.Url;
import com.shortener.url.model.User;
import com.shortener.url.service.UrlServiceImpl;
import com.shortener.url.service.UserService;
import com.shortener.url.util.Util;
@RunWith(MockitoJUnitRunner.class)

class UrlServiceImplTest {


    @InjectMocks
    UrlServiceImpl urlService;
    @Mock
    UserServiceImpl userService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    final public void testSetNumberOfVisits() {

        Url url =getUrl();

        Map<String, Url> myUrlList = new HashMap<>();
        myUrlList.put(url.getShortUrl(), url);

        User user = getFakeUser( myUrlList);

        when(userService.getCurrentUser()).thenReturn(user);


        assertEquals(1, urlService.setNumberOfVisitsForThisUrl("http://localhost:8090/vojo.com/shortUrl"));
    }


    private User getFakeUser(Map<String, Url> myUrlList) {
        return User.builder()
                .accountId("vojo").myUrlList(myUrlList)
                .build();
    }


    private Url getUrl() {
        return Url.builder()
                .realUrl("real url....")
                .shortUrl("http://localhost:8090/vojo.com/shortUrl")
                .build();
    }
//	@Test
//	final public void testCreateShortUrl() throws MalformedURLException {
//		Url url = getUrl();
//
//		Map<String, Url> myUrlList = new HashMap<>();
//		myUrlList.put(url.getShortUrl(), url);
//
//
//		User user = getFakeUser(myUrlList);
//		when(Util.generateString(5)).thenReturn("shortUrl");
//		when(userService.getCurrentUser()).thenReturn(user);
//
//		assertEquals(url.getShortUrl(), urlService.createShortUrl(url));
//	}

}
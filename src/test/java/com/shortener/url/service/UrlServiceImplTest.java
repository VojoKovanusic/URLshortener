package com.shortener.url.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.HashMap;
import java.util.Map;

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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public final void testSetNumberOfVisits() {

        Url url =getUrl();

        Map<String, Url> myUrlList = new HashMap<>();
        myUrlList.put(url.getShortUrl(), url);

        User user = getUser( myUrlList);

        when(userService.getCurrentUser()).thenReturn(user);


        assertEquals(1, urlService.setNumberOfVisitsForThisUrl("http://localhost:8090/vojo.com/shortUrl"));
    }


    private User getUser(Map<String, Url> myUrlList) {
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
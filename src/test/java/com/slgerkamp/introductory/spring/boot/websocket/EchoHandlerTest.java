package com.slgerkamp.introductory.spring.boot.websocket;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import org.hamcrest.Matchers.*;

import rx.Observable;
import rx.subjects.PublishSubject;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0"})
public class EchoHandlerTest {

 	@Value("${local.server.port}")
 	int port;
 	private final ResponseEvent event = new ResponseEvent();
 	
 	static class ResponseEvent {
 		final PublishSubject<Response> responseEvent = PublishSubject.create();	
 		public Observable<Response> responsed(){
 			return responseEvent;
 		} 		
 	}
	
	@Before
	public void 事前準備(){
		RestAssured.port = port;
	}
	@Test
	public void チャットをする(){
		
		event.responsed().forEach(res -> System.out.println(res.body().asString()));
		
		Response response = get("/echo").thenReturn();
		event.responseEvent.onNext(response);

	}
}

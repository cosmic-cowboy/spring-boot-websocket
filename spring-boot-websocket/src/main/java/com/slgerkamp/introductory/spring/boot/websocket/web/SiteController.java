package com.slgerkamp.introductory.spring.boot.websocket.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Webサイトページ表示用コントローラーです。
 *
 */
@Controller
public class SiteController {

	@RequestMapping("/")
	public String chat(){
		return "index.html";
	}
}

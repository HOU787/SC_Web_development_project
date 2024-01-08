package net.kdigital.skyscrapper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping({"", "/"})
	public String index() {
		return "index";
	}

	
	@GetMapping("/denied")
    public String dispDenied() {
        return "/denied";
    }
	
	@GetMapping("/intro")
    public String dispIntro() {
        return "/intro";
    }
	
}

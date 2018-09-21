package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@RequestMapping("/viral")
	public String index() {
		return "viral";
	}
	
	@RequestMapping("/challenge")
	public String challenge(@RequestParam(value = "name") String name, Model model) {
		model.addAttribute("name", name);
		return "challenge";
	}
	
	@RequestMapping(value= {"/challenge", "challenge/{name}"})
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());
		}else {
			model.addAttribute("name", "KB");
		}
		return "challenge";
	}
	
	@RequestMapping("/generator")
	public String viralGenerator(@RequestParam(value = "a", required = false, defaultValue = "0") int a, @RequestParam(value="b", required = false, defaultValue = "0") int b, Model model) {
		String res = "";
		
		if(a==0 && b==0) {
			res = "hm";
		}
		
		else if(a == 0 && b > 0) {
			for (int i = 0; i < b; i++) {
				res += "hm ";
			}
		}
		
		else if (a>0 && b==0) {
			res += "h";
			for (int i = 0; i < a ; i++) {
				res+="m";
			}
		}
		
		else if (a>0 && b>0) {
			res += "h";
			for(int i = 0; i<a; i++) {
				res+="m";
			}
			
			res += " ";
			String kata = "";
			for(int i=0; i<b; i++) {
				kata += res;
			}
			res = kata;
		}
		model.addAttribute("a", a);
		model.addAttribute("b", b);
		model.addAttribute("hasil", res);
		return "generator";
	}
}

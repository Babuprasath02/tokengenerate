package com.example.LoginLogout.registeration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/registration")
public class RegisterationController {
	
	private RegisterationService registerationService;
	
	public RegisterationController(RegisterationService registerationService) {
		super();
		this.registerationService = registerationService;
	}

	@PostMapping
	public String register(@RequestBody RegisterRequest request) {
		return registerationService.register(request);
	}
	 @GetMapping(path = "confirm")
	    public String confirm(@RequestParam("token") String token) {
	        return registerationService.confirmToken(token);
	    }


}

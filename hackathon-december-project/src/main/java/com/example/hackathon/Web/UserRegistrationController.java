package com.example.hackathon.Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.hackathon.Model.User;
import com.example.hackathon.Service.UserService;
import com.example.hackathon.Web.Dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	private UserService userService;

	
	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
//	@ModelAttribute("user")
//	public UserRegistrationDto userRegistrationDto()
//	{
//		return new UserRegistrationDto();
//	}
	
	@GetMapping
	public String showUserRegistrationForm(Model model)
	{
		model.addAttribute("user", new UserRegistrationDto());
		return "registration";
	}
	
	@PostMapping
	public String userRegistrationAccount(@ModelAttribute("user") @Validated UserRegistrationDto registrationDto,
	BindingResult result)
	{

	        User existing = userService.findByEmail(registrationDto.getEmail());
	        if (existing != null) {
	            result.rejectValue("email", null, "There is already an account registered with that email");
	        }

	        if (result.hasErrors()) {
	            return "redirect:/registration?error";
	        } 
	        
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
	
	
	

}

package com.alex.magazin_deviceuri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.alex.magazin_deviceuri.entity.Utilizator;
import com.alex.magazin_deviceuri.entity.UtilizatorChangePasswordDTO;
import com.alex.magazin_deviceuri.repository.UtilizatorRepository;
import com.alex.magazin_deviceuri.services.UtilizatorChangePasswordService;
import com.alex.magazin_deviceuri.services.UtilizatorRegisterService;

@Controller
public class AuthController {
	
	@Autowired
    private UtilizatorRegisterService utilizatorRegisterService;
	
	@Autowired
	private UtilizatorRepository utilizatorRepository;
	
	@Autowired
	private UtilizatorChangePasswordService utilizatorChangePasswordService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
    public String showLogin(Model model) {
		model.addAttribute("user", new Utilizator());
        return "login";
    }
	
	@PostMapping("/login")
    public String login(@ModelAttribute("user") Utilizator utilizator, Model model) {
        return "login";
    }
	
	@GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new Utilizator());
        return "register";
    }
	
	@PostMapping("/register")
    public String register(@ModelAttribute("user") Utilizator utilizator, Model model) {
		
		boolean succes = utilizatorRegisterService.register(utilizator);
		
		if(succes)
		{
			model.addAttribute("regSuccess", "Registration successful! Please login.");
			return "login";
		}
		else
		{
			model.addAttribute("regError", "Username already exists!");
			return "register";
		}
    }
	
	@GetMapping("/changePassword")
    public String ShowChangePasswordForm(Model model) {
        model.addAttribute("userChPass", new UtilizatorChangePasswordDTO());
        return "changePassword";
    }

	@PostMapping("/changePassword")
    public String ChangePasswordForm(@ModelAttribute("userChPass") UtilizatorChangePasswordDTO userChPass, Model model) {
		
		Utilizator utilizator = new Utilizator();
		utilizator.setUsername(userChPass.getUsername());
		if(utilizatorRepository.findByUsername(utilizator.getUsername()).isPresent()) {
			var utilizatorRepositoryFound = utilizatorRepository.findByUsername(utilizator.getUsername()).get();
			if(passwordEncoder.matches(userChPass.getParolaVeche(), utilizatorRepositoryFound.getParola()))
			{
				if(userChPass.getParolaNoua().compareTo(userChPass.getParolaNouaDinNou()) == 0)
				{
					utilizator.setId(utilizatorRepositoryFound.getId());
					utilizator.setRol(utilizatorRepositoryFound.getRol());
					utilizator.setNume(utilizatorRepositoryFound.getNume());
					utilizatorChangePasswordService.changePassword(utilizator, userChPass.getParolaNoua());
					model.addAttribute("chPass_Success", "Password has been changed successfully!");
			        return "redirect:/login";
					
				}
				else
				{
					model.addAttribute("chPass_NewPassError", "New password and confirmation password do not match!");
				}
			}
			else
			{
				model.addAttribute("chPass_PassError", "Wrong password!");
			}
		}
		else
		{
			model.addAttribute("chPass_UsernameError", "The user with the specified username does not exist!");
		}
		return "changePassword";
    }
	
}

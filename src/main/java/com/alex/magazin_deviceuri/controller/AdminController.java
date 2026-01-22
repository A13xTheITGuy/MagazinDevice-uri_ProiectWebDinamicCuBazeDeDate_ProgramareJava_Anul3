package com.alex.magazin_deviceuri.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alex.magazin_deviceuri.entity.UtilizatorEditDTO;
import com.alex.magazin_deviceuri.entity.Utilizator;
import com.alex.magazin_deviceuri.repository.UtilizatorRepository;
import com.alex.magazin_deviceuri.services.UtilizatorChangePasswordService;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

	@Autowired
	private UtilizatorChangePasswordService utilizatorChangePasswordService;
	
	@Autowired
	private UtilizatorRepository utilizatorRepository;
	
	@GetMapping("/users")
	public String showUsers(Model model)
	{
		model.addAttribute("listaUseri", utilizatorRepository.findAll());
	    return "administrareUtilizatori";
	}
	
	@PostMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable Long id, Authentication authentication)
	{
		String utilizatorName = authentication.getName();
    	Utilizator utilizator = utilizatorRepository.findByUsername(utilizatorName).orElseThrow();
    	Long userId = utilizator.getId();
    	
    	if(userId != id) {
    		utilizatorRepository.deleteById(id);
    	}
    	
	    return "redirect:/admin/users";
	}
	    
	    
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable Long id, Model model)
	{
		Optional<Utilizator> utilizatorOpt =  Optional.ofNullable(utilizatorRepository.findAll().stream().filter(u -> u.getId() == id).findFirst().orElseThrow());
	    if(utilizatorOpt.isPresent())
	    {
	    	var utilizatorObj = utilizatorOpt.get();
	    	UtilizatorEditDTO utilizatorEditDTO = new UtilizatorEditDTO();
	    	utilizatorEditDTO.setId(id);
	    	utilizatorEditDTO.setNume(utilizatorObj.getNume());
	    	utilizatorEditDTO.setUsername(utilizatorObj.getUsername());
	    	utilizatorEditDTO.setRol(utilizatorObj.getRol());

	    	model.addAttribute("editUser", utilizatorEditDTO);
	    	System.out.println("utilizatorEditDTO: " + utilizatorEditDTO);
	        return "editUser";
	    }
	    return "redirect:/admin/users";
	}
	    
	@PostMapping("/users/edit/{id}")
	public String editUser( @ModelAttribute("editUser") UtilizatorEditDTO utilizatorEditDTO,
	    					@PathVariable Long id,
	    					Model model, Authentication authentication)
	{
		System.out.println(" | INFO | utilizatorEditDTO: " + utilizatorEditDTO);
		if( utilizatorEditDTO.getParolaNoua().isEmpty() &&
				utilizatorEditDTO.getParolaNouaDinNou().isEmpty())
		{
			System.out.println(" | INFO | Nu s-a setat parola noua!");
			
			Optional<Utilizator> editUserOpt = Optional.ofNullable(utilizatorRepository.findAll().stream().filter(u -> u.getId() == id).findFirst().orElseThrow());
			
			if(editUserOpt.isPresent())
			{
				var editUser = editUserOpt.get();
				editUser.setId(utilizatorEditDTO.getId());
				editUser.setNume(utilizatorEditDTO.getNume());
				editUser.setUsername(utilizatorEditDTO.getUsername());
				editUser.setRol(utilizatorEditDTO.getRol());

				System.out.println(" | INFO | editUser: " + editUser);
				utilizatorRepository.save(editUser);
			}	
		}
		else
		{
			if( !utilizatorEditDTO.getParolaNoua().isEmpty() &&
					!utilizatorEditDTO.getParolaNouaDinNou().isEmpty() )
			{
				if(utilizatorEditDTO.getParolaNoua().compareTo(utilizatorEditDTO.getParolaNouaDinNou()) == 0)
				{
					Utilizator editUser = new Utilizator();
					editUser.setId(utilizatorEditDTO.getId());
					editUser.setNume(utilizatorEditDTO.getNume());
					editUser.setUsername(utilizatorEditDTO.getUsername());
					editUser.setRol(utilizatorEditDTO.getRol());
					System.out.println(" | SUCCESS | Parola noua setata cu succes!" + " | " + editUser + utilizatorEditDTO.getParolaNoua());
					boolean succes = utilizatorChangePasswordService.changePassword(editUser, utilizatorEditDTO.getParolaNoua());
				}
			}
				
		}
		
	    return "redirect:/admin/users";
	}
}

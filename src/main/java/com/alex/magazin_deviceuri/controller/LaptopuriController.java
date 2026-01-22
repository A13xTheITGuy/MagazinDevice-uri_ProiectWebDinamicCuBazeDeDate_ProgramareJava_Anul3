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

import com.alex.magazin_deviceuri.entity.Laptop;
import com.alex.magazin_deviceuri.entity.Utilizator;
import com.alex.magazin_deviceuri.repository.LaptopRepository;
import com.alex.magazin_deviceuri.repository.UtilizatorRepository;

@Controller
@RequestMapping("/laptopuri")
@PreAuthorize("hasAnyRole('EDITOR','ADMIN')")
public class LaptopuriController {
	
	@Autowired
	private LaptopRepository laptopRepository;
	
	@Autowired
	private UtilizatorRepository utilizatorRepository;
	
	@GetMapping("/add")
    public String addForm(Model model) {
		model.addAttribute("laptop", new Laptop());
        return "addLaptop";
    }
	
    @PostMapping("/add")
    public String save(
    		@ModelAttribute("laptop") Laptop newLaptop,
    		Model model, Authentication authentication) {
    	
    	String utilizatorName = authentication.getName();
    	Utilizator utilizator = utilizatorRepository.findByUsername(utilizatorName).orElseThrow();
    	newLaptop.setId_utilizator(utilizator.getId());
    	laptopRepository.save(newLaptop);
        return "redirect:/lista-deviceuri";
    }
    
    @PostMapping("delete/{id}")
    public String deleteLaptop(@PathVariable Long id)
    {
    	laptopRepository.deleteById(id);
    	return "redirect:/lista-deviceuri";
    }
    
    @GetMapping("edit/{id}")
    public String editLaptop(@PathVariable Long id, Model model)
    {
    	Optional<Laptop> laptopOpt =  laptopRepository.findAll().stream().filter(l -> l.getId_laptop() == id).findFirst();
    	if(laptopOpt.isPresent())
    	{
    		var laptopObj = laptopOpt.get();
    		model.addAttribute("editLaptop", laptopObj);
        	return "editLaptop";
    	}
    	return "redirect:/lista-deviceuri";
    }
    
    @PostMapping("edit/{id}")
    public String editLaptop(@ModelAttribute("editLaptop") Laptop editLaptop,
    						@PathVariable Long id,
    						Model model, Authentication authentication)
    {
    	String utilizatorName = authentication.getName();
    	Utilizator utilizator = utilizatorRepository.findByUsername(utilizatorName).orElseThrow();
    	editLaptop.setId_laptop(id);
    	editLaptop.setId_utilizator(utilizator.getId());
    	laptopRepository.save(editLaptop);
        return "redirect:/lista-deviceuri";
    }
}

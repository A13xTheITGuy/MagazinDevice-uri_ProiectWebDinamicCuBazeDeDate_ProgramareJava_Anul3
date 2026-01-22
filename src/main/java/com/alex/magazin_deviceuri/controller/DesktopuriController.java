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

import com.alex.magazin_deviceuri.entity.Desktop;
import com.alex.magazin_deviceuri.entity.Utilizator;
import com.alex.magazin_deviceuri.repository.DesktopRepository;
import com.alex.magazin_deviceuri.repository.UtilizatorRepository;

@Controller
@RequestMapping("/desktopuri")
@PreAuthorize("hasAnyRole('EDITOR','ADMIN')")
public class DesktopuriController {
	
	@Autowired
	private UtilizatorRepository utilizatorRepository;
	
	@Autowired
	private DesktopRepository desktopRepository;
	
	@GetMapping("/add")
    public String addForm(Model model) {
		model.addAttribute("desktop", new Desktop());
        return "addDesktop";
    }

    @PostMapping("/add")
    public String save(
    		@ModelAttribute("desktop") Desktop newDesktop,
    		Model model, Authentication authentication) {
    	
    	String utilizatorName = authentication.getName();
    	Utilizator utilizator = utilizatorRepository.findByUsername(utilizatorName).orElseThrow();
    	newDesktop.setId_utilizator(utilizator.getId());
    	desktopRepository.save(newDesktop);
        return "redirect:/lista-deviceuri";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteDesktop(@PathVariable Long id)
    {
    	desktopRepository.deleteById(id);
    	return "redirect:/lista-deviceuri";
    }
    
    
    @GetMapping("edit/{id}")
    public String editDesktop(@PathVariable Long id, Model model)
    {
    	Optional<Desktop> desktopOpt = desktopRepository.findAll().stream().filter(d -> d.getId_desktop() == id).findFirst();
    	if(desktopOpt.isPresent())
    	{
    		var desktopObj = desktopOpt.get();
    		model.addAttribute("editDesktop", desktopObj);
        	return "editDesktop";
    	}
    	return "redirect:/lista-deviceuri";
    }
    
    @PostMapping("edit/{id}")
    public String editDesktop(@ModelAttribute("editDesktop") Desktop editDesktop,
    						@PathVariable Long id,
    						Model model, Authentication authentication)
    {
    	String utilizatorName = authentication.getName();
    	Utilizator utilizator = utilizatorRepository.findByUsername(utilizatorName).orElseThrow();
    	editDesktop.setId_desktop(id);
    	editDesktop.setId_utilizator(utilizator.getId());
    	desktopRepository.save(editDesktop);
        return "redirect:/lista-deviceuri";
    }
}

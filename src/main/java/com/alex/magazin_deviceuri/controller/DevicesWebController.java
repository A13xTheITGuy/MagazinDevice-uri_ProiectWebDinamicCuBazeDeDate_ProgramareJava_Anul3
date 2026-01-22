package com.alex.magazin_deviceuri.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.alex.magazin_deviceuri.repository.LaptopRepository;
import com.alex.magazin_deviceuri.repository.DesktopRepository;

import com.alex.magazin_deviceuri.entity.Laptop;
import com.alex.magazin_deviceuri.entity.Desktop;

@Controller
public class DevicesWebController {
	@Autowired
	private LaptopRepository laptopRepository;
	@Autowired
	private DesktopRepository desktopRepository;
	
	//private static String seperators = ",./<>?;'`:\"[]{}_-+\\|!@#$%^&*()~ =	";
	private static String nonSeperators = "[^a-z0-9]+";
	
	private static boolean isInteger(String token) {
	    return token.matches("\\d+");
	}
	
	private static boolean isFloat(String token) {
	    return token.matches("\\d+(\\.\\d+)?");
	}
	
	private static boolean floatEquals(Float a, Float b) {
	    if (a == null || b == null) return false;
	    return Math.abs(a - b) < 0.01f;
	}
	
	public static boolean cautareDupaTip(Class<?> clasa, Object object, String searchString)
	{
		if(searchString == null || searchString.isBlank() || searchString.isEmpty()) return true;
		searchString = searchString.toLowerCase();

		System.out.println(" | INFO | searchString= " + searchString);
		String[] tokens =  searchString.split(nonSeperators);
		System.out.println(" | INFO | search tokens: " + Arrays.toString(tokens));
		
		int nrTokens = tokens.length;
		int contor = 0;
		
		for(String token:tokens)
		{
			if(token.isBlank()) continue;
			
			boolean tokenMatched = false;
			
			Integer tokenInt = null;
	        Float tokenFloat = null;

	        if (isInteger(token)) {
	        	tokenInt = Integer.parseInt(token);
	        } else if (isFloat(token)) {
	        	tokenFloat = Float.parseFloat(token);
	        }
			
			if(Desktop.class.equals(clasa))
			{
				Desktop desktop = (Desktop)object;
				
				tokenMatched = desktop.getNume_sistem()!=null && desktop.getNume_sistem().toLowerCase().contains(token) ||
						desktop.getCuloare()!=null &&desktop.getCuloare().toLowerCase().contains(token) ||
						desktop.getProcesor()!=null &&desktop.getProcesor().toLowerCase().contains(token) ||
						desktop.getPlaca_video()!=null &&desktop.getPlaca_video().toLowerCase().contains(token) ||
						desktop.getTip_stocare()!=null &&desktop.getTip_stocare().toLowerCase().contains(token) ||
						desktop.getSistem_de_operare()!=null &&desktop.getSistem_de_operare().toLowerCase().contains(token) ||
						desktop.getTip_RAM()!=null &&desktop.getTip_RAM().toLowerCase().contains(token);
				
				if(tokenInt != null && !tokenMatched)
				{
					tokenMatched = Objects.equals(desktop.getCapacitate_RAM(), tokenInt) ||
							Objects.equals(desktop.getCapacitate_stocare(), tokenInt) ||
							Objects.equals(desktop.getFrecventa_RAM(), tokenInt);
				}
				
				if(tokenFloat != null && !tokenMatched)
				{
					tokenMatched = floatEquals(desktop.getPret(), tokenFloat);
				}
			}
			
			if(Laptop.class.equals(clasa))
			{
				Laptop laptop = (Laptop)object;
				
				tokenMatched = laptop.getNume_laptop()!=null && laptop.getNume_laptop().toLowerCase().contains(token) ||
						laptop.getCuloare()!=null &&laptop.getCuloare().toLowerCase().contains(token) ||
						laptop.getProcesor()!=null &&laptop.getProcesor().toLowerCase().contains(token) ||
						laptop.getPlaca_video()!=null &&laptop.getPlaca_video().toLowerCase().contains(token) ||
						laptop.getTip_stocare()!=null &&laptop.getTip_stocare().toLowerCase().contains(token) ||
						laptop.getSistem_de_operare()!=null &&laptop.getSistem_de_operare().toLowerCase().contains(token) ||
						laptop.getTip_RAM()!=null &&laptop.getTip_RAM().toLowerCase().contains(token) || 
						laptop.getRezolutie_ecran()!=null &&laptop.getRezolutie_ecran().toLowerCase().contains(token);
				
				if(tokenInt != null && !tokenMatched)
				{
					tokenMatched = Objects.equals(laptop.getCapacitate_RAM(), tokenInt) ||
	                        Objects.equals(laptop.getCapacitate_stocare(), tokenInt) ||
	                        Objects.equals(laptop.getFrecventa_RAM(), tokenInt);
				}
				
				
				if (tokenFloat != null && !tokenMatched) 
				{
					tokenMatched = token.contains(String.valueOf(laptop.getPret())) ||
								token.contains(String.valueOf(laptop.getDimensiune_ecran()));
				}
			}
			
			if (!tokenMatched){
				return false;
			}
		}	
		return true;
	}
	
	@GetMapping("/lista-deviceuri")
	public String getListaDeviceuri(@RequestParam(required = false) Integer capacitate_ram_form,
									@RequestParam(required = false) String tip_RAM_form,
									@RequestParam(required = false) Integer cap_stocare_form,
									@RequestParam(required = false) String sistem_de_operare_form,
									@RequestParam(required = false) String cautare_form,
									@RequestParam(required = false) Float pret_min_form,
									@RequestParam(required = false) Float pret_max_form,
			Model model, Authentication authentication) {
		
		Integer capRAMDefault=0;
		String tipRAMDefault="null";
		String OSDefault="null";
		Integer capStocareDefault = 0;
		
		String allDevMessage = "Toate device-urile";
		String filterString = "Filtre";
		String allLaptopsMessage = "Toate laptopurile";
		String allDesktopsMessage = "Toate desktopurile";
		String userName = authentication.getName();
		String welcomeMessage = "Bine ati venit, " + userName + "!";
		String searchString = "Cautare";
		System.out.println("user: " + userName);
		model.addAttribute("username", userName);
		model.addAttribute("allDevMessage",allDevMessage);
		model.addAttribute("allLaptopsMessage",allLaptopsMessage);
		model.addAttribute("allDesktopsMessage",allDesktopsMessage);
		model.addAttribute("welcomeMessage",welcomeMessage);
		model.addAttribute("filterString", filterString);
		model.addAttribute("searchString", searchString);
		
		List<Laptop> listaLaptopuri;
		List<Desktop> listaDesktopuri;
		
		if(capacitate_ram_form!=null || tip_RAM_form!=null || sistem_de_operare_form!=null || cap_stocare_form!=null || (cautare_form!=null && !cautare_form.isEmpty() && !cautare_form.isBlank()) || pret_min_form!=null || pret_max_form!=null) {
			listaLaptopuri = laptopRepository.findAll().stream()
					.filter( l -> cautareDupaTip(Laptop.class, l, cautare_form))
					.filter( l -> capacitate_ram_form==capRAMDefault || Objects.equals(l.getCapacitate_RAM(), capacitate_ram_form)  ) //l.getCapacitate_RAM() == capacitate_ram_form)
					.filter( l -> tip_RAM_form.equals(tipRAMDefault) || tip_RAM_form.isEmpty() || tip_RAM_form.isBlank() || (l.getTip_RAM().equals(tip_RAM_form)) )
					.filter( l -> cap_stocare_form.equals(capStocareDefault) || (l.getCapacitate_stocare() == cap_stocare_form ))
					.filter( l -> sistem_de_operare_form.equals(OSDefault) || sistem_de_operare_form.isEmpty() || sistem_de_operare_form.isBlank() || (l.getSistem_de_operare().equals(sistem_de_operare_form)) )
					.filter( l -> pret_min_form == null || (l.getPret() >= pret_min_form ))
					.filter( l -> pret_max_form == null || (l.getPret() <= pret_max_form ))
					.collect(Collectors.toList());
		    
			listaDesktopuri = desktopRepository.findAll().stream()
					.filter( d -> cautareDupaTip(Desktop.class, d, cautare_form))
					.filter( d -> capacitate_ram_form==capRAMDefault || Objects.equals(d.getCapacitate_RAM(), capacitate_ram_form) )
					.filter( d -> tip_RAM_form.equals(tipRAMDefault) ||  tip_RAM_form.isEmpty() || tip_RAM_form.isBlank() || (d.getTip_RAM().equals(tip_RAM_form)) )
					.filter( d -> cap_stocare_form.equals(capStocareDefault) || (d.getCapacitate_stocare() == cap_stocare_form ))
					.filter( d -> sistem_de_operare_form.equals(OSDefault) || sistem_de_operare_form.isEmpty() || sistem_de_operare_form.isBlank() || (d.getSistem_de_operare().equals(sistem_de_operare_form)) )
					.filter( d -> pret_min_form == null || (d.getPret() >= pret_min_form ))
					.filter( d -> pret_max_form == null || (d.getPret() <= pret_max_form ))
					.collect(Collectors.toList());
		}
		else {
			listaLaptopuri =  laptopRepository.findAll();
			listaDesktopuri = desktopRepository.findAll();
		}
		
		model.addAttribute("capRAMOld", capacitate_ram_form);
		model.addAttribute("tipRAMOld", tip_RAM_form);
		model.addAttribute("capStocareOld", cap_stocare_form);
		model.addAttribute("OSOld", sistem_de_operare_form);
		model.addAttribute("cautareOld", cautare_form);
		model.addAttribute("pretMinOld", pret_min_form);
		model.addAttribute("pretMaxOld", pret_max_form);
		
		model.addAttribute("listaLaptopuri",listaLaptopuri);
		model.addAttribute("listaDesktopuri",listaDesktopuri);
		return "deviceuri";
	}
}

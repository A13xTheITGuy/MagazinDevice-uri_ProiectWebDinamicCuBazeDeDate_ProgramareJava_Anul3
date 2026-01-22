package com.alex.magazin_deviceuri.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.alex.magazin_deviceuri.entity.Utilizator;
import com.alex.magazin_deviceuri.repository.UtilizatorRepository;

@Service
public class UtilizatorRegisterService {
	
	@Autowired
	private UtilizatorRepository utilizatorRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean register(Utilizator utilizator)
	{
		if(utilizatorRepository.findByUsername(utilizator.getUsername()).isPresent()) {
			return false;
		}
		
		Utilizator utilizatorNou = new Utilizator();
		utilizatorNou.setNume(utilizator.getNume());
		utilizatorNou.setUsername(utilizator.getUsername());
		utilizatorNou.setParola(passwordEncoder.encode(utilizator.getParola()));
		utilizatorNou.setRol("ROLE_USER"); // ROLE_USER
		
		System.out.println(" | INFO | utilizatorNou: " + utilizatorNou);
		
		utilizatorRepository.save(utilizatorNou);
		return true;
	}
}

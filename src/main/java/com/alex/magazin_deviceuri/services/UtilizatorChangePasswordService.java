package com.alex.magazin_deviceuri.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alex.magazin_deviceuri.entity.Utilizator;
import com.alex.magazin_deviceuri.repository.UtilizatorRepository;

@Service
public class UtilizatorChangePasswordService {

	@Autowired
	private UtilizatorRepository utilizatorRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean changePassword(Utilizator utilizator, String paronaNoua)
	{
		if(!utilizatorRepository.findByUsername(utilizator.getUsername()).isPresent()) {
			return false;
		}
		
		utilizator.setParola(passwordEncoder.encode(paronaNoua));
		System.out.println(" | INFO | utilizator: " + utilizator);
		utilizatorRepository.save(utilizator);
		return true;
	}
}

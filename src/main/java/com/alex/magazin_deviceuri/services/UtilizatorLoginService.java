package com.alex.magazin_deviceuri.services;

import com.alex.magazin_deviceuri.entity.Utilizator;
import com.alex.magazin_deviceuri.repository.UtilizatorRepository;

import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UtilizatorLoginService implements UserDetailsService{
	@Autowired
	private UtilizatorRepository utilizatorRepository;
	
	public UtilizatorLoginService(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Utilizator> utlilizator = utilizatorRepository.findByUsername(username);
		
		System.out.println(" | INFO | Utilizator: " + utlilizator);
		
		if(utlilizator.isPresent()) {
			var utlilizatorObj = utlilizator.get();
			return User.builder()
					.username(utlilizatorObj.getUsername())
					.password(utlilizatorObj.getParola())
					.authorities(utlilizatorObj.getRol())
					.build();
		}
		else {
			throw new UsernameNotFoundException ("| ERROR | User not found!");
		}
	}
}

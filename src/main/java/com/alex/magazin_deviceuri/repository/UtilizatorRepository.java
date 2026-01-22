package com.alex.magazin_deviceuri.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alex.magazin_deviceuri.entity.Utilizator;

@Repository
public interface UtilizatorRepository extends JpaRepository<Utilizator, Long> {
	 Optional<Utilizator> findByUsername(String username);
}

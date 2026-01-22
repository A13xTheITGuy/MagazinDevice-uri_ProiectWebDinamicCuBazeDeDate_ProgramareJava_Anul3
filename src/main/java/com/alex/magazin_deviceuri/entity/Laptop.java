package com.alex.magazin_deviceuri.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="laptopuri")
public class Laptop {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_laptop;
	private long id_utilizator;
	private String nume_laptop;
	private String procesor;
	private String placa_video;
	private int capacitate_RAM;
	private String tip_RAM;
	private int frecventa_RAM;
	private String tip_stocare;
	private int capacitate_stocare;
	private String rezolutie_ecran;
	private float dimensiune_ecran;
	private String sistem_de_operare;
	private String culoare;
	private float pret;
	public long getId_laptop() {
		return id_laptop;
	}
	public void setId_laptop(long id_laptop) {
		this.id_laptop = id_laptop;
	}
	public long getId_utilizator() {
		return id_utilizator;
	}
	public void setId_utilizator(long id_utilizator) {
		this.id_utilizator = id_utilizator;
	}
	public String getNume_laptop() {
		return nume_laptop;
	}
	public void setNume_laptop(String nume_laptop) {
		this.nume_laptop = nume_laptop;
	}
	public String getProcesor() {
		return procesor;
	}
	public void setProcesor(String procesor) {
		this.procesor = procesor;
	}
	public String getPlaca_video() {
		return placa_video;
	}
	public void setPlaca_video(String placa_video) {
		this.placa_video = placa_video;
	}
	public int getCapacitate_RAM() {
		return capacitate_RAM;
	}
	public void setCapacitate_RAM(int capacitate_RAM) {
		this.capacitate_RAM = capacitate_RAM;
	}
	public String getTip_RAM() {
		return tip_RAM;
	}
	public void setTip_RAM(String tip_RAM) {
		this.tip_RAM = tip_RAM;
	}
	public int getFrecventa_RAM() {
		return frecventa_RAM;
	}
	public void setFrecventa_RAM(int frecventa_RAM) {
		this.frecventa_RAM = frecventa_RAM;
	}
	public String getTip_stocare() {
		return tip_stocare;
	}
	public void setTip_stocare(String tip_stocare) {
		this.tip_stocare = tip_stocare;
	}
	public int getCapacitate_stocare() {
		return capacitate_stocare;
	}
	public void setCapacitate_stocare(int capacitate_stocare) {
		this.capacitate_stocare = capacitate_stocare;
	}
	public String getRezolutie_ecran() {
		return rezolutie_ecran;
	}
	public void setRezolutie_ecran(String rezolutie_ecran) {
		this.rezolutie_ecran = rezolutie_ecran;
	}
	public float getDimensiune_ecran() {
		return dimensiune_ecran;
	}
	public void setDimensiune_ecran(float dimensiune_ecran) {
		this.dimensiune_ecran = dimensiune_ecran;
	}
	public String getSistem_de_operare() {
		return sistem_de_operare;
	}
	public void setSistem_de_operare(String sistem_de_operare) {
		this.sistem_de_operare = sistem_de_operare;
	}
	public String getCuloare() {
		return culoare;
	}
	public void setCuloare(String culoare) {
		this.culoare = culoare;
	}
	public float getPret() {
		return pret;
	}
	public void setPret(float pret) {
		this.pret = pret;
	}
	@Override
	public String toString() {
		return "Laptop [id_laptop=" + id_laptop + ", id_utilizator=" + id_utilizator + ", nume_laptop=" + nume_laptop
				+ ", procesor=" + procesor + ", placa_video=" + placa_video + ", capacitate_RAM=" + capacitate_RAM
				+ ", tip_RAM=" + tip_RAM + ", frecventa_RAM=" + frecventa_RAM + ", tip_stocare=" + tip_stocare
				+ ", capacitate_stocare=" + capacitate_stocare + ", rezolutie_ecran=" + rezolutie_ecran
				+ ", dimensiune_ecran=" + dimensiune_ecran + ", sistem_de_operare=" + sistem_de_operare + ", culoare="
				+ culoare + ", pret=" + pret + "]";
	}
	
	
	
}

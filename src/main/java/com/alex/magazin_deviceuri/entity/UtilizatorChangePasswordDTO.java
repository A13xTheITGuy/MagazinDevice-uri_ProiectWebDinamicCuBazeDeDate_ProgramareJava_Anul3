package com.alex.magazin_deviceuri.entity;

public class UtilizatorChangePasswordDTO {
    private String username;
    private String parolaVeche;
    
    private String parolaNoua;
    private String parolaNouaDinNou;
    
	@Override
	public String toString() {
		return "UtilizatorChangePasswordDTO [username=" + username + ", parolaVeche=" + parolaVeche + ", parolaNoua="
				+ parolaNoua + ", parolaNouaDinNou=" + parolaNouaDinNou + "]";
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getParolaVeche() {
		return parolaVeche;
	}
	public void setParolaVeche(String parolaVeche) {
		this.parolaVeche = parolaVeche;
	}
	public String getParolaNoua() {
		return parolaNoua;
	}
	public void setParolaNoua(String parolaNoua) {
		this.parolaNoua = parolaNoua;
	}
	public String getParolaNouaDinNou() {
		return parolaNouaDinNou;
	}
	public void setParolaNouaDinNou(String parolaNouaDinNou) {
		this.parolaNouaDinNou = parolaNouaDinNou;
	}
}

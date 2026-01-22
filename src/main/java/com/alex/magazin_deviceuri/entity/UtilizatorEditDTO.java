package com.alex.magazin_deviceuri.entity;

public class UtilizatorEditDTO {
	private Long id;
    private String nume;
    private String username;
    private String rol;

    private String parolaNoua;
    private String parolaNouaDinNou;
    
	@Override
	public String toString() {
		return "UtilizatorEditDTO [id=" + id + ", nume=" + nume + ", username=" + username + ", rol=" + rol
				 + ", parolaNoua=" + parolaNoua + ", parolaNouaDinNou=" + parolaNouaDinNou + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
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

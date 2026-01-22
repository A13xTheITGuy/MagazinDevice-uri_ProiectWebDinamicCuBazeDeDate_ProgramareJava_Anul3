package com.alex.magazin_deviceuri;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.alex.magazin_deviceuri.controller.DevicesWebController;
import com.alex.magazin_deviceuri.entity.Laptop;

@SpringBootTest
class MagazinDeviceuriApplicationTests {

	@Test
	void searchForGPUModel() {
		Laptop laptop = new Laptop();
		laptop.setPlaca_video("NVIDIA RTX 5060");
		
		boolean result = DevicesWebController.cautareDupaTip(Laptop.class, laptop, "rtx 5060");
		
		assertTrue(result);
	}
	
	@Test
	void searchForGPUModelAndRam() {
		Laptop laptop = new Laptop();
		laptop.setPlaca_video("NVIDIA RTX 5070");
		laptop.setCapacitate_RAM(32);
		
		boolean result = DevicesWebController.cautareDupaTip(Laptop.class, laptop, "rtx 5070 32 GB");
		
		assertTrue(result);
	}

}

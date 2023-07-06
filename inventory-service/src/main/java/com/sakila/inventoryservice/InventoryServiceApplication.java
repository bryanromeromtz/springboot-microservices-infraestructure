package com.sakila.inventoryservice;

import com.sakila.inventoryservice.model.Inventory;
import com.sakila.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("esp32_devkit_v1");
			inventory.setQuantity(500);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("Arduino_Uno_R3");
			inventory1.setQuantity(380);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("RASPBERRY_PI_PICO_W");
			inventory2.setQuantity(0);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}
}

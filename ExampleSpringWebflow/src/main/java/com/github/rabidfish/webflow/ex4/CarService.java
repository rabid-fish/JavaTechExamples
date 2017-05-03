package com.github.rabidfish.webflow.ex4;

import org.springframework.stereotype.Service;

import com.github.rabidfish.AbstractRepository;

@Service
public class CarService extends AbstractRepository<Car> {

	public boolean initializeTestData() {
		reset();
		save(createCar("Dodge", "Neon"));
		save(createCar("Mazda", "Miata"));
		save(createCar("Volkswagen", "Golf"));
		return true;
	}
	
	Car createCar(String make, String model) {
		
		Car car = new Car();
		car.setMake(make);
		car.setModel(model);
		
		return car;
	}

}

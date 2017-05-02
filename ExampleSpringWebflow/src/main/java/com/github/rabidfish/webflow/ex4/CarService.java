package com.github.rabidfish.webflow.ex4;

import org.springframework.stereotype.Service;

import com.github.rabidfish.AbstractRepository;

@Service
public class CarService extends AbstractRepository<Car> {

	public boolean initializeTestData() {
		reset();
		save(createCar(1L, "Dodge", "Neon"));
		save(createCar(2L, "Mazda", "Miata"));
		save(createCar(3L, "Volkswagen", "Golf"));
		return true;
	}
	
	Car createCar(long id, String make, String model) {
		
		Car car = new Car();
		car.setId(id);
		car.setMake(make);
		car.setModel(model);
		
		return car;
	}

}

package com.wildcodeschool.spring.bookstore.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.wildcodeschool.spring.bookstore.entity.carpool.Car;
import com.wildcodeschool.spring.bookstore.repository.CarRepository;

@SpringBootTest
@Transactional
class CarControllerTest {

	private MockMvc mock;

	@Autowired
	private CarController underTest;

	@Autowired
	private CarRepository carRepo;

	@BeforeEach
	void setup() {
		mock = MockMvcBuilders.standaloneSetup(underTest).build();
	}

	@Test
	void shouldReadAllCars() throws Exception {
		// Given | Arrange

		// When | Act
		MvcResult result = mock.perform(MockMvcRequestBuilders.get("/cars")).andReturn();
		// Then | Assert
		List<Car> cars = getCarsFromModel(result);
		assertThat(cars).hasSize(0);
	}

	@Test
	void shouldFindOneCar() throws Exception {
		// Given | Arrange
		givenACarInTheDatabase("Tesla Model 3");
		// When | Act
		MvcResult result = mock.perform(MockMvcRequestBuilders.get("/cars")).andReturn();
		// Then | Assert
		List<Car> books = getCarsFromModel(result);
		assertThat(books).hasSize(1);
	}

	@Test
	void shouldFindTwoCars() throws Exception {
		// Given | Arrange
		givenACarInTheDatabase("Tesla Model 3");
		givenACarInTheDatabase("Renault Zoe");
		// When | Act
		MvcResult result = mock.perform(MockMvcRequestBuilders.get("/cars")).andReturn();
		// Then | Assert
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
		List<Car> books = getCarsFromModel(result);
		assertThat(books).hasSize(2);
	}

	@Test
	void shouldBeAbleToUploadACar() throws Exception {
		// Given | Arrange
		Car carForUpload = new Car();
		carForUpload.setModel("Tesla Model 3000");
		// When
		MvcResult result = mock.perform(MockMvcRequestBuilders.post("/car/upsert")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).flashAttr("car", carForUpload)).andReturn();
		// Then
		assertThat(result.getResponse().getStatus()).isEqualTo(302);
		List<Car> results = carRepo.findAll(Example.of(carForUpload));
		assertThat(results).hasSize(1);
		assertThat(results.get(0).getModel()).isEqualTo("Tesla Model 3000");
	}

	@Test
	void shouldBeAbleToModifyACar() throws Exception {
		// Given | Arrange
		Car existingCar = givenACarInTheDatabase("Tesla Model 3");
		Car modifiedCar = new Car();
		modifiedCar.setId(existingCar.getId());
		modifiedCar.setModel("Tesla Model 4");
		// When
		MvcResult result = mock.perform(MockMvcRequestBuilders.post("/car/upsert")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).flashAttr("car", modifiedCar)).andReturn();
		// Then
		assertThat(result.getResponse().getStatus()).isEqualTo(302);
		Optional<Car> resultCar = carRepo.findById(existingCar.getId());
		assertThat(resultCar).isPresent();
		assertThat(resultCar.get().getModel()).isEqualTo("Tesla Model 4");
	}

	@Test
	void shouldBeAbleToDeleteACar() throws Exception {
		// Given | Arrange
		Car existingCar = givenACarInTheDatabase("Tesla Model 3");
		// When
		MvcResult result = mock.perform(MockMvcRequestBuilders.get("/car/" + existingCar.getId() + "/delete")).andReturn();
		// Then
		assertThat(result.getResponse().getStatus()).isEqualTo(302);
		Optional<Car> resultCar = carRepo.findById(existingCar.getId());
		assertThat(resultCar).isEmpty();
	}

	private List<Car> getCarsFromModel(MvcResult result) {
		ModelMap attributeMap = result.getModelAndView().getModelMap();
		@SuppressWarnings("unchecked")
		List<Car> cars = (List<Car>) attributeMap.get("cars");
		return cars;
	}

	private Car givenACarInTheDatabase(String model) {
		Car car = new Car();
		car.setModel(model);
		return carRepo.save(car);
	}

}

package io.mglobe.toqio.banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.mglobe.toqio.banking.api.AccountController;
import io.mglobe.toqio.banking.entity.AccountEntity;
import io.mglobe.toqio.banking.service.AccountService;

@EnableAutoConfiguration()
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	AccountService accountService;
	
	@Mock
	AccountController accountController;

	//@Test
	public void get_allAccounts_returnsOkWithListOfAccounts() throws Exception {

		List<AccountEntity> accountList = new ArrayList<>();
		AccountEntity account1 = new AccountEntity("1","Bic Swift 1","1","iban 1");
		AccountEntity account2 = new AccountEntity("2","Bic Swift 2","2","iban 2");
		AccountEntity account3 = new AccountEntity("3","Bic Swift 3","3","iban 3");
		
		accountList.add(account1);
		accountList.add(account2);
		accountList.add(account3);

		when(accountController.findAll()).thenReturn(ResponseEntity.status(HttpStatus.OK).body(accountList));
		assertEquals(200, HttpStatus.OK);
		// Mocking out the account service
		//Mockito.when(accountService.findAllAccounts()).thenReturn(ResponseEntity.status(HttpStatus.ACCEPTED).body(accountList));

		//mockMvc.perform(MockMvcRequestBuilders.get("/banking/v1/accounts").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		//.andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(accountList)))
	}
/*
	@Test
	public void post_createsNewVehicle_andReturnsObjWith201() throws Exception {
		Vehicle vehicle = new Vehicle("AD23E5R98EFT3SL00", "Ford", "Fiesta", 2016, false);

		Mockito.when(vechicleService.createVehicle(Mockito.any(Vehicle.class))).thenReturn(vehicle);

		// Build post request with vehicle object payload
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/demo/create/vehicle")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(vehicle));

		mockMvc.perform(builder).andExpect(status().isCreated()).andExpect(jsonPath("$.vin", is("AD23E5R98EFT3SL00")))
				.andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(vehicle)));
	}

	@Test
	public void post_submitsInvalidVehicle_WithEmptyMake_Returns400() throws Exception {
		// Create new vehicle with empty 'make' field
		Vehicle vehicle = new Vehicle("AD23E5R98EFT3SL00", "", "Firebird", 1982, false);

		String vehicleJsonString = this.mapper.writeValueAsString(vehicle);

		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/demo/create/vehicle/")
				.contentType(MediaType.APPLICATION_JSON).content(vehicleJsonString)).andExpect(status().isBadRequest());

		// @Valid annotation in controller will cause exception to be thrown
		assertEquals(MethodArgumentNotValidException.class,
				resultActions.andReturn().getResolvedException().getClass());
		assertTrue(resultActions.andReturn().getResolvedException().getMessage().contains("'make' field was empty"));
	}

	@Test
	public void put_updatesAndReturnsUpdatedObjWith202() throws Exception {
		Vehicle vehicle = new Vehicle("AD23E5R98EFT3SL00", "Ford", "Fiesta", 2016, false);

		Mockito.when(vechicleService.updateVehicle("AD23E5R98EFT3SL00", vehicle)).thenReturn(vehicle);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.put("/demo/update/vehicle/AD23E5R98EFT3SL00", vehicle).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(this.mapper.writeValueAsBytes(vehicle));

		mockMvc.perform(builder).andExpect(status().isAccepted()).andExpect(jsonPath("$.vin", is("AD23E5R98EFT3SL00")))
				.andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(vehicle)));
	}

	@Test
	public void delete_deleteVehicle_Returns204Status() throws Exception {
		String vehicleVin = "AD23E5R98EFT3SL00";

		VehicleService serviceSpy = Mockito.spy(vechicleService);
		Mockito.doNothing().when(serviceSpy).deleteVehicle(vehicleVin);

		mockMvc.perform(MockMvcRequestBuilders.delete("/demo/vehicles/AD23E5R98EFT3SL00")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

		verify(vechicleService, times(1)).deleteVehicle(vehicleVin);
	}

	@Test
	public void get_vehicleByVin_ThrowsVehicleNotFoundException() throws Exception {

		// Return an empty Optional object since we didn't find the vin
		Mockito.when(vechicleService.getVehicleByVin("AD23E5R98EFT3SL00")).thenReturn(Optional.empty());

		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.get("/demo/vehicles/AD23E5R98EFT3SL00").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

		assertEquals(VehicleNotFoundException.class, resultActions.andReturn().getResolvedException().getClass());
		assertTrue(resultActions.andReturn().getResolvedException().getMessage()
				.contains("Vehicle with VIN (" + "AD23E5R98EFT3SL00" + ") not found!"));
	}
	*/
}

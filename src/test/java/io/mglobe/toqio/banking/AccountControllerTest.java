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
}

package io.mglobe.toqio.banking.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.mglobe.toqio.banking.entity.AccountEntity;
import io.mglobe.toqio.banking.service.AccountService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/banking/v1/accounts")
@ApiOperation(value ="Account Entity Operations", notes="Account Entity Operations")
public class AccountController {
	@Autowired
	AccountService accountService;
	//new card creation
	@RequestMapping(method = RequestMethod.POST, consumes = {"application/json","application/xml"}, produces = {"application/json","application/xml"})
	@ApiOperation(value = "New Account", notes = "New Account")
	public @ResponseBody ResponseEntity<AccountEntity> createAccount(@RequestBody AccountEntity req){
		System.out.println("Account creation : controller");
		return accountService.createAccount(req);
	}
	
	//card update
	@PutMapping(consumes = {"application/json","application/xml"}, produces = {"application/json","application/xml"})
	@ApiOperation(value = "Edit Account Details", notes = "Edit Account Details")
	public ResponseEntity<AccountEntity> updateAccount(@RequestBody AccountEntity req){
		return accountService.updateAccount(req);
	}
	
	// Get card details for a given card Id
	@GetMapping(value="/{accountId}", produces= {"application/json","application/xml"})
	@ApiOperation(value = "Get Account Details", notes = "Get Account Details")
	public ResponseEntity<AccountEntity> findByAccountId(@PathVariable String accountId){
		return accountService.findByAccountId(accountId);
	}
	
	//retrieval of all cards details
	@GetMapping(produces= {"application/json","application/xml"})
	@ApiOperation(value = "Get All Accounts Details", notes = "Get All Accounts Details")
	public @ResponseBody ResponseEntity<List<AccountEntity>> findAll(){
		System.out.println("accountService.findAllAccounts(): "+accountService.findAllAccounts());
		return accountService.findAllAccounts();
	}
	
	//delete card record for a given card id
	@DeleteMapping(value="/{accountId}", produces= {"application/json","application/xml"})
	@ApiOperation(value = "Delete Account Entry", notes = "Delete Account Entry")
	public ResponseEntity<AccountEntity> remove(@PathVariable String accountId){
		return accountService.deleteEntry(accountId);
	}
}

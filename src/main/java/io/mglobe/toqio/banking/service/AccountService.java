package io.mglobe.toqio.banking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.mglobe.toqio.banking.entity.AccountEntity;
import io.mglobe.toqio.banking.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepo;

	//new account creation
	public ResponseEntity<AccountEntity> createAccount(AccountEntity req) {
		System.out.println("************* Create Account Resquest: "+req);
		
		if(accountRepo.findByAccountId(req.getAccountId()) ==null) {
			AccountEntity account = accountRepo.save(req);
			System.out.println("************* Create Account Response: "+account);
			return ResponseEntity.status(HttpStatus.CREATED).body(account);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		

	}
	//account update
	public ResponseEntity<AccountEntity> updateAccount(AccountEntity req){
		AccountEntity accountRecord = accountRepo.findByAccountId(req.getAccountId());
		if(accountRecord !=null) {
			accountRecord.setBicSwift(req.getBicSwift());
			accountRecord.setIban(req.getIban());
			accountRecord.setClientId(req.getClientId());
			//MongoConfig.mongoTemplate.save(accountRecord,"AccountEntity");
			//AccountEntity updatedRecord= accountRepo.save(accountRecord);
			try {
				accountRepo.save(accountRecord);
				return ResponseEntity.status(HttpStatus.OK).body(req);
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
			/*
			if(updatedRecord !=null) {
				return ResponseEntity.status(HttpStatus.OK).body(updatedRecord);
			}else {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			}*/
			
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}
	// Get account details for a given account Id
	public ResponseEntity<AccountEntity> findByAccountId(String accountId) {
		AccountEntity accountDetails = accountRepo.findByAccountId(accountId);
		
		if(accountDetails !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(accountDetails);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	//retrieval of all account details
	public ResponseEntity<List<AccountEntity>> findAllAccounts(){
		List<AccountEntity> accounts=new ArrayList<>();
	    accountRepo.findAll().stream().forEach(acc ->{
	    	accounts.add(acc);
	    });
		System.out.println("****************** findAll: Accounts ==="+accounts);
		if(!accounts.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(accounts);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	//delete account record for a given account id
	public ResponseEntity<AccountEntity> deleteEntry(String accountId){
		AccountEntity accountDetails = accountRepo.findByAccountId(accountId);
		if(accountDetails !=null) {
			accountRepo.deleteById(accountId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
}

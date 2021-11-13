package io.mglobe.toqio.banking.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.mglobe.toqio.banking.entity.AccountEntity;

@Repository
public interface AccountRepository extends MongoRepository<AccountEntity, Object>{
	public AccountEntity findByAccountId(String accountId);
	
	public List<AccountEntity> findAll();

	public List<AccountEntity> findByClientId(String clientId);
}

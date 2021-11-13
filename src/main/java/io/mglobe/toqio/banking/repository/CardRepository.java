package io.mglobe.toqio.banking.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.mglobe.toqio.banking.entity.CardEntity;

@Repository
public interface CardRepository extends MongoRepository<CardEntity, String>{
	
	public CardEntity findByCardId(String cardId);
	
	public List<CardEntity> findAll();

	public CardEntity findByAccountId(String accountId);

}

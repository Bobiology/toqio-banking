package io.mglobe.toqio.banking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.mglobe.toqio.banking.entity.AccountEntity;
import io.mglobe.toqio.banking.entity.CardEntity;
import io.mglobe.toqio.banking.repository.AccountRepository;
import io.mglobe.toqio.banking.repository.CardRepository;

@Service
public class CardService {
	@Autowired
	CardRepository cardRepo;
	
	@Autowired
	AccountRepository accountRepo;

	//new card creation
	public ResponseEntity<CardEntity> createCard(CardEntity req) {
		if(cardRepo.findByCardId(req.getCardId()) ==null) {
			CardEntity card = cardRepo.save(req);
			return ResponseEntity.status(HttpStatus.CREATED).body(card);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}
	//card update
	public ResponseEntity<CardEntity> updateCard(CardEntity req){
		CardEntity cardRecord = cardRepo.findByCardId(req.getCardId());
		if(cardRecord !=null) {
			cardRecord.setCardAlias(req.getCardAlias());
			//cardRecord.setCardType(req.getCardType());
			CardEntity updatedRecord = cardRepo.save(cardRecord);
			if( updatedRecord !=null) {
				return ResponseEntity.status(HttpStatus.OK).body(updatedRecord);
			}else {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			}
			
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}
	// Get card details for a given card Id
	public ResponseEntity<CardEntity> findByCardId(String cardId) {
		CardEntity cardDetails = cardRepo.findByCardId(cardId);
		
		if(cardDetails !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(cardDetails);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	//retrieval of all cards details
	public ResponseEntity<List<CardEntity>> findAllCards(){
		List<CardEntity> cards=new ArrayList<>();
	    cardRepo.findAll().stream().forEach(card ->{
	    	cards.add(card);
	    });
		System.out.println("****************** findAll: Accounts ==="+cards);
		if(!cards.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(cards);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	//delete card record for a given card id
	public ResponseEntity<CardEntity> deleteEntry(String cardId){
		CardEntity cardDetails = cardRepo.findByCardId(cardId);
		if(cardDetails !=null) {
			cardRepo.deleteById(cardId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	public ResponseEntity<List<CardEntity>> getCardByClientId(String clientId) {
		List<CardEntity> cards = new ArrayList<>();
		accountRepo.findByClientId(clientId).forEach(acc ->{
			cards.add(cardRepo.findByAccountId(acc.getAccountId()));
		});
		if(!cards.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(cards);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
}

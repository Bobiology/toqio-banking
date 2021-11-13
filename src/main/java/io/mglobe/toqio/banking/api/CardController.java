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
import org.springframework.web.bind.annotation.RestController;

import io.mglobe.toqio.banking.entity.CardEntity;
import io.mglobe.toqio.banking.service.CardService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/banking/v1/cards")
@ApiOperation(value ="Card Entity Operations", notes="Card Entity Operations")

public class CardController {
	@Autowired
	CardService cardService;
	
	//new card creation
	@RequestMapping(value="", method = RequestMethod.POST, consumes = {"application/json","application/xml"}, produces = {"application/json","application/xml"})
	@ApiOperation(value = "Create New Card API", notes = "New Card")
	public ResponseEntity<CardEntity> createCard(@RequestBody CardEntity cardRequest){
		return cardService.createCard(cardRequest);
	}
	
	//card update
	@PutMapping(consumes = {"application/json","application/xml"}, produces = {"application/json","application/xml"})
	@ApiOperation(value = "Edit card details for a provided card Id", notes = "Edit Card Details")
	public ResponseEntity<CardEntity> updateCard(@RequestBody CardEntity cardRequest){
		return cardService.updateCard(cardRequest);
	}
	
	// Get card details for a given card Id
	@GetMapping(value="/{cardId}", produces= {"application/json","application/xml"})
	@ApiOperation(value = "List card details for a provided card Id", notes = "Get Card Details")
	public ResponseEntity<CardEntity> findByCardId(@PathVariable String cardId){
		return cardService.findByCardId(cardId);
	}
	
	//retrieval of all cards details
	@GetMapping(produces= {"application/json","application/xml"})
	@ApiOperation(value = "List all cards details", notes = "Get All Cards Details")
	public ResponseEntity<List<CardEntity>> findAll(){
		return cardService.findAllCards();
	}
	
	//delete card record for a given card id
	@DeleteMapping(value="/{cardId}", produces= {"application/json","application/xml"})
	@ApiOperation(value = "Delete card entry for a given card Id", notes = "Delete Card Entry")
	public ResponseEntity<CardEntity> remove(@PathVariable String cardId){
		return cardService.deleteEntry(cardId);
	}
	
	// Get card details using client Id
		@GetMapping(value="/cards/{clientId}", produces= {"application/json","application/xml"})
		@ApiOperation(value = "List card details for a provided client Id", notes = "Get Card Details")
		public ResponseEntity<List<CardEntity>> getCardByClientId(@PathVariable String clientId){
			return cardService.getCardByClientId(clientId);
		}
}

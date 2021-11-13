package io.mglobe.toqio.banking.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "CARD")
@XmlRootElement(name="Card")
public class CardEntity {

	@Id
	@Indexed(unique = true)
	@Field(name="CARD_ID")
	private String cardId;
	@Field(name="ACCOUNT_ID")
	private String accountId;
	@Field(name="CARD_ALIAS")
	private String cardAlias;
	@Field(name="CARD_TYPE")
	private String cardType;
	
	public CardEntity() {
		
	}

	public CardEntity(String accountId, String cardAlias, String cardId, String cardType) {
		super();
		this.accountId = accountId;
		this.cardAlias = cardAlias;
		this.cardId = cardId;
		this.cardType = cardType;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCardAlias() {
		return cardAlias;
	}

	public void setCardAlias(String cardAlias) {
		this.cardAlias = cardAlias;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Override
	public String toString() {
		return "CardEntity [cardId=" + cardId + ", accountId=" + accountId + ", cardAlias=" + cardAlias + ", cardType="
				+ cardType + "]";
	}
	

}

package io.mglobe.toqio.banking.entity;


import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="ACCOUNT")
@XmlRootElement(name="Account")
public class AccountEntity {

	@Id
	@Indexed(unique = true)
	@Field(name="ACCOUNT_ID")
	private String accountId;
	@Field(name="BIC_SWIFT")
	private String bicSwift;
	
	@Field(name="CLIENT_ID")
	private String clientId;
	
	@Field(name="IBAN")
	private String iban;
	
	
	public AccountEntity() {

	}
	public AccountEntity(String accountId, String bicSwift, String clientId, String iban) {
		this.accountId = accountId;
		this.bicSwift = bicSwift;
		this.clientId = clientId;
		this.iban = iban;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getBicSwift() {
		return bicSwift;
	}
	public void setBicSwift(String bicSwift) {
		this.bicSwift = bicSwift;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	
	@Override
	public String toString() {
		return "AccountEntity [accountId=" + accountId + ", bicSwift=" + bicSwift + ", clientId=" + clientId + ", iban="
				+ iban + "]";
	}
	
	
}
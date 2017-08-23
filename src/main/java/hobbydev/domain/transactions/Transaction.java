package hobbydev.domain.transactions;

import hobbydev.domain.core.IdentifiedEntityInterface;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "transactions")
public class Transaction implements IdentifiedEntityInterface {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name="currency")
	private String currency;
	@Column(name = "tx_timestamp")
	private ZonedDateTime timestamp;
	@Column(name = "sender")
	private String sender;
	@Column(name = "recipient")
	private String recipient;
	
	public Transaction(){}
	
	public Transaction(
			TransactionParticipant sender,
			TransactionParticipant recipient,
			BigDecimal amount,
			String currency) {
		
		this.amount = amount;
		this.currency = currency;
		this.timestamp = ZonedDateTime.now();
		this.sender = sender.toTransactionParticipantString();
		this.recipient = recipient.toTransactionParticipantString();
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public ZonedDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getRecipient() {
		return recipient;
	}
	
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
}

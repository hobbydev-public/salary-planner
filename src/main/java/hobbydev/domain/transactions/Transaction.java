package hobbydev.domain.transactions;

import hobbydev.domain.assets.Asset;
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
	
	@Column(name="from_string")
	private String from;
	@Column(name = "to_string")
	private String to;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private Asset sender;
	@ManyToOne
	@JoinColumn(name = "recipient_id")
	private Asset recipient;
	
	public Transaction(){}
	
	public Transaction(
			Asset sender,
			Asset recipient,
			BigDecimal amount,
			String currency) {
		
		this.amount = amount;
		this.currency = currency;
		this.timestamp = ZonedDateTime.now();
		this.sender = sender;
		this.recipient = recipient;
		
		this.from = sender == null? "":sender.toTransactionParticipantString();
		this.to = recipient == null? "":recipient.toTransactionParticipantString();
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
	
	public Asset getSender() {
		return sender;
	}
	
	public void setSender(Asset sender) {
		this.sender = sender;
	}
	
	public Asset getRecipient() {
		return recipient;
	}
	
	public void setRecipient(Asset recipient) {
		this.recipient = recipient;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
}

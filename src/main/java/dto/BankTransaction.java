package dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class BankTransaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	double deposit;
	double withdraw;
	double balance;
	LocalDateTime dateTime;
	
	@ManyToOne
	Customer customer;
	
	@OneToMany(cascade=CascadeType.ALL)
	List<BankTransaction> transactions;
}

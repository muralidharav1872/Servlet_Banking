package dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class BankAccount 
{
	@Id
	@GeneratedValue(generator="acno")
	@SequenceGenerator(initialValue=456987121, allocationSize=1, sequenceName="acno", name = "acno")
	long acno;
	String type;
	double amount;
	boolean status;
	double ac_limit;
	
	@ManyToOne
	Customer customer;

	@OneToMany(cascade= CascadeType.ALL)
	List<BankTransaction> transaction;
	
	
	
	
	
}

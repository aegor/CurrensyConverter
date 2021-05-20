package com.akulovs.CurrencyConverter.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Size(min = 4, max = 12)
	@Pattern(regexp = "^[A-Za-z0-9]*$")
	private String login;

	@NotBlank
	private String password;

	@NotBlank
	@Size(max = 30)
	private String firstName;

	@Getter @Setter private String lastName;

	@Email
	@NotBlank
	private String emailId;

	//@OneToMany(mappedBy="customer", cascade = CascadeType.ALL, orphanRemoval = true)
	@ElementCollection
	private Map<String, @DecimalMin("0.0") @Digits(integer = 11, fraction = 2) BigDecimal> currencies;

	public Customer(String login, String firstName, String lastName, String emailId) {
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}
}

package com.akulovs.CurrencyConverter.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import static com.akulovs.CurrencyConverter.util.CurrencyConverterUtil.getFormattedAmount;
import static com.akulovs.CurrencyConverter.util.CurrencyConverterUtil.getFormattedDate;

@Entity
@Data
@NoArgsConstructor
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Size(max = 3, min = 3)
	private String sourceCurrency;

	@NotNull
	@DecimalMin("0.0")
	@Digits(integer = 11, fraction = 2)
	@Getter(value=AccessLevel.NONE)
	@Setter(value=AccessLevel.NONE)
	private BigDecimal amountToConvert;

	public BigDecimal getAmountToConvert() {
		return amountToConvert;
	}

	public void setAmountToConvert(String amountToConvert) {
		this.amountToConvert = new BigDecimal(amountToConvert);
	}

	@NotBlank
	@Size(max = 3, min = 3)
	private String targetCurrency;

	@NotNull
	private Double rate;

	public CustomerOrder(String sourceCurrency, BigDecimal amountToConvert, String targetCurrency, Double rate) {
		this.sourceCurrency = sourceCurrency;
		this.amountToConvert = amountToConvert;
		this.targetCurrency = targetCurrency;
		this.rate = rate;
	}

	public CustomerOrder(CustomerOrder order){
		this.sourceCurrency = order.sourceCurrency;
		this.amountToConvert = order.amountToConvert;
		this.targetCurrency = order.targetCurrency;
		this.rate = order.rate;
	}
}

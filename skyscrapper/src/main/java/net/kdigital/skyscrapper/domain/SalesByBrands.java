package net.kdigital.skyscrapper.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SalesByBrands {
	private String salesYr;
	private String brand;
	private int sales;
} 

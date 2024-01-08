package net.kdigital.skyscrapper.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SentimentCount {
	private String companyName;
	private int count1;
	private int count0;
}

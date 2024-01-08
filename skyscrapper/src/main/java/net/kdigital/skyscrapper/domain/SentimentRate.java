package net.kdigital.skyscrapper.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SentimentRate {
	private String company;
	private int rate;
}

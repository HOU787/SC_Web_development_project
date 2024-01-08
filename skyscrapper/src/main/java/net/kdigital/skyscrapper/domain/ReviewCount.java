package net.kdigital.skyscrapper.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReviewCount {
	private String companyNn;
	private int reviewCnt;
}

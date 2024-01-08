package net.kdigital.skyscrapper.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
	private int product_id;
	private String product_nm;
	private String product_info;
	private String originalfile;
	private String savedfilename;
	private String member_id;
	private String category_nm;
	private String payment_term;
	private String regdate;
    private int pending_status;
    private int block_status;

}
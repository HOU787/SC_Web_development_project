package net.kdigital.skyscrapper.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReplyProduct {
	private String brand_name;
    private String product_name;
    private String shape_product;
    private LocalDateTime regdate;
    private Integer rating;
    private String title_main;
    private String channel;
}

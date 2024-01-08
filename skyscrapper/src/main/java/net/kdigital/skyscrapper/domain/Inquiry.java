package net.kdigital.skyscrapper.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Inquiry {
   
   private int inq_id;
   private String snd_id;
   private String rcv_id;
   private String product_id;
   private String input_date;
   private String message;
   private int pending_status;
   private int block_status;
   }
   
   

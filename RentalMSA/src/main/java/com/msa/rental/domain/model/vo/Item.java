package com.msa.rental.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	private Integer no;
	private String title;

	public static Item sample() {
		return new Item(1, "Sample Item");
	}

}

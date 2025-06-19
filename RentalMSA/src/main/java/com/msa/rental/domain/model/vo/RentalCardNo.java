package com.msa.rental.domain.model.vo;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCardNo {
	private String no;

	public static RentalCardNo createRentalCardNo() {
		UUID uuid = UUID.randomUUID();
		String year = String.valueOf(LocalDate.now().getYear());
		String str = year + "-" + uuid.toString();
		RentalCardNo rentalCardNo = new RentalCardNo();
		rentalCardNo.setNo(str);
		return rentalCardNo;
	}


	public static RentalCardNo sample() {
		return RentalCardNo.createRentalCardNo();
	}

	public static void main(String[] args) {
		System.out.println(RentalCardNo.sample());

	}
}

package com.msa.rental.domain.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.domain.model.vo.LateFee;
import com.msa.rental.domain.model.vo.RentalCardNo;
import com.msa.rental.domain.model.vo.RentalStatus;
import com.msa.rental.domain.model.vo.ReturnItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentalCard {

	private RentalCardNo rentalCardNo;
	private IDName member;
	private RentalStatus rentStatus;
	private LateFee lateFee;
	private List<RentalItem> rentalItemList = new ArrayList<>();
	private List<ReturnItem> returnItemList = new ArrayList<>();

	public static RentalCard sample() {
		RentalCard rentalCard = new RentalCard();
		rentalCard.setRentalCardNo(RentalCardNo.sample());
		rentalCard.setMember(IDName.sample());
		rentalCard.setRentStatus(RentalStatus.RENT_AVAILABLE);
		rentalCard.setLateFee(LateFee.sample());
		return rentalCard;
	}

	private void addRentalItem(RentalItem rentalItem) {
		rentalItemList.add(rentalItem);
	}

	private void removeRentalItem(RentalItem rentalItem) {
		rentalItemList.remove(rentalItem);
	}

	private void addReturnItem(ReturnItem returnItem) {
		returnItemList.add(returnItem);
	}

	// 대여카드 생성
	public static RentalCard createRentalCard(IDName creator) {
		RentalCard rentalCard = new RentalCard();
		rentalCard.setRentalCardNo(RentalCardNo.createRentalCardNo());
		rentalCard.setMember(creator);
		rentalCard.setRentStatus(RentalStatus.RENT_AVAILABLE);
		rentalCard.setLateFee(LateFee.createLateFee());
		return rentalCard;
	}

	// 대여 처리
	public RentalCard rentItem(Item item) throws Exception {
		checkRentalAvailable();
		this.addRentalItem(RentalItem.createRentalItem(item));

		return this;
	}

	private void checkRentalAvailable() throws Exception {
		if (this.rentStatus == RentalStatus.RENT_UNAVAILABLE) {throw new IllegalAccessException("대여 불가 상태입니다.");}
		if (this.rentalItemList.size() > 5) {throw new IllegalAccessException("5권 이상이면 대여 불가");}
	}

	public RentalCard returnItem(Item item, LocalDate returnDate) {
		RentalItem rentalItem = this.rentalItemList.stream()
			.filter(i -> i.getItem().equals(item))
			.findFirst()
			.get();

		calculateLateFee(rentalItem, returnDate);

		this.addReturnItem(ReturnItem.createReturnItem(rentalItem));
		this.removeRentalItem(rentalItem);
		return this;
	}

	private void calculateLateFee(RentalItem rentalItem, LocalDate returnDate) {
		// 반납일이 연체일 이후(=연체)일 때만 연체료 계산
		if (returnDate.isAfter(rentalItem.getOverdueDate())) {
			long daysLate = ChronoUnit.DAYS.between(rentalItem.getOverdueDate(), returnDate);
			long fee = daysLate * 10L;
			LateFee addPoint = this.lateFee.addPoint(fee);
			this.lateFee.setPoint(addPoint.getPoint());
		}
	}

	public RentalCard overdueItem(Item item) {

		RentalItem rentalItem = this.rentalItemList.stream().filter(i -> i.getItem().equals(item)).findFirst().get();
		rentalItem.setOverdued(true);
		this.rentStatus = RentalStatus.RENT_UNAVAILABLE;

		//억지 연체 생성 - 실제로 불필요
		rentalItem.setOverdueDate(LocalDate.now().minusDays(1));
		return this;
	}

	public long makeAvailableRental(long point) throws Exception {

		if (!this.rentalItemList.isEmpty()) {
			throw new IllegalArgumentException("모든 도서가 반납되어야 정지를 해제할 수 있습니다.");
		}

		if (this.getLateFee().getPoint() != point) {
			throw new IllegalArgumentException("해당 포인트로 연체를 해제할 수 없습니다.");
		}

		this.setLateFee(lateFee.removePoint(point));
		if (this.getLateFee().getPoint() == 0) {
			this.rentStatus = RentalStatus.RENT_AVAILABLE;
		}
		return this.getLateFee().getPoint();
	}

	public static void main(String[] args) {
		System.out.println(sample());
	}
}

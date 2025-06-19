package com.msa.rental.application.usecase;

import com.msa.rental.application.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.application.framework.web.dto.UserItemInputDTO;

public interface RentItemUsecase {

	public RentalCardOutputDTO rentItem(UserItemInputDTO rental) throws Exception;
}

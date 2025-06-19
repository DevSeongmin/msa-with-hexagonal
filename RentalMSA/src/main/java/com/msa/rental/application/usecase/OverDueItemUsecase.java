package com.msa.rental.application.usecase;

import com.msa.rental.application.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.application.framework.web.dto.UserItemInputDTO;

public interface OverDueItemUsecase {

	public RentalCardOutputDTO overDueItem(UserItemInputDTO rental) throws Exception;
}

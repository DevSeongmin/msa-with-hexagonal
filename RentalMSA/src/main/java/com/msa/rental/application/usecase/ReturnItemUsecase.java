package com.msa.rental.application.usecase;

import com.msa.rental.application.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.application.framework.web.dto.UserInputDTO;
import com.msa.rental.application.framework.web.dto.UserItemInputDTO;

public interface ReturnItemUsecase {
	public RentalCardOutputDTO returnItem(UserItemInputDTO returnDto) throws Exception;
}

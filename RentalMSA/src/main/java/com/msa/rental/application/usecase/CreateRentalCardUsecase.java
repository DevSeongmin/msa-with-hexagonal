package com.msa.rental.application.usecase;

import com.msa.rental.application.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.application.framework.web.dto.UserInputDTO;

public interface CreateRentalCardUsecase {
	public RentalCardOutputDTO createRentalCard(UserInputDTO userInputDto);
}

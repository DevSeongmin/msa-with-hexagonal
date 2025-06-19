package com.msa.rental.application.usecase;

import com.msa.rental.application.framework.web.dto.ClearOverdueInfoDto;
import com.msa.rental.application.framework.web.dto.RentalResultOutputDTO;

public interface ClearOverdueUsecase {
	RentalResultOutputDTO clearOverdue(ClearOverdueInfoDto clearOverdueInfoDto) throws Exception;
}

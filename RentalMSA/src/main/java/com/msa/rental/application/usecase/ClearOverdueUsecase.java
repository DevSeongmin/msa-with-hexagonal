package com.msa.rental.application.usecase;

import com.msa.rental.application.framework.web.dto.ClearOverdueInfoDTO;
import com.msa.rental.application.framework.web.dto.RentalResultOutputDTO;

public interface ClearOverdueUsecase {
	RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDto) throws Exception;
}

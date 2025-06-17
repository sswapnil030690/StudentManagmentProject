package com.swapnil.student.service;

import com.swapnil.student.model.Fee;
import com.swapnil.student.model.Receipt;

public interface FeeService {
	
	Receipt collectFee(Long studentId, Fee fee);

}

package com.swapnil.receipt.service;

import com.swapnil.receipt.entity.Receipt;
import com.swapnil.receipt.model.ReceiptResponse;

public interface ReceiptService {
	
	Receipt saveReceipt(Receipt receipt);
	
	ReceiptResponse viewReceiptByStudentId(Long StudentId);
	
	

}

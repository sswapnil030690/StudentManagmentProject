package com.swapnil.receipt.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swapnil.receipt.dao.ReceiptRepository;
import com.swapnil.receipt.entity.Receipt;
import com.swapnil.receipt.exception.ReceiptNotFoundException;
import com.swapnil.receipt.feign.IStudentClient;
import com.swapnil.receipt.model.ReceiptResponse;
import com.swapnil.receipt.model.Student;
import com.swapnil.receipt.service.ReceiptService;

@Service
public class ReceiptServiceImpl implements ReceiptService {
	
	@Autowired
	ReceiptRepository receiptRepository;
	
	@Autowired
	IStudentClient studentClient;
	

	@Override
	public Receipt saveReceipt(Receipt receipt) {
		return receiptRepository.save(receipt);
	}


	@Override
	public ReceiptResponse viewReceiptByStudentId(Long studentId) {
		Receipt receipt = receiptRepository.findFirstByStudentId(studentId);
		if(receipt != null) {
			ReceiptResponse receiptResponse = new ReceiptResponse();
			Student student = new Student();
			ResponseEntity<Student> studentById = studentClient.getStudentById(studentId);
			ModelMapper modelmapper = new ModelMapper();
			modelmapper.map(studentById.getBody(),student);
			receiptResponse.setCardNumber(receipt.getCardNumber());
			receiptResponse.setReferenceNumber(receipt.getReferenceNumber());
			receiptResponse.setCardType(receipt.getCardType());
			receiptResponse.setDateTime(receipt.getDateTime());
			receiptResponse.setFee(receipt.getFee());
			receiptResponse.setStudent(student);
			return receiptResponse;
		}
				
		else
				throw new ReceiptNotFoundException("No receipt found for student ID: " + studentId );
		
	}

}

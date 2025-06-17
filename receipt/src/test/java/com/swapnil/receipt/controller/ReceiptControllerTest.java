package com.swapnil.receipt.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.swapnil.receipt.controller.ReceiptController;
import com.swapnil.receipt.model.ReceiptResponse;
import com.swapnil.receipt.model.Student;
import com.swapnil.receipt.service.ReceiptService;

@SpringBootTest
public class ReceiptControllerTest {
	
	@InjectMocks
	ReceiptController reseController;
	
	@Mock
	ReceiptService receiptService;
	
	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void viewReceipt() {
		when(receiptService.viewReceiptByStudentId(anyLong())).thenReturn(getReceiptResponse());
		ResponseEntity<?> receipt = reseController.viewReceipt((long) 2);
		Assert.assertTrue(receipt != null && HttpStatus.OK.equals(receipt.getStatusCode()));
	}

	private ReceiptResponse getReceiptResponse() {
		ReceiptResponse receipt = new ReceiptResponse();
		Student student = new Student();
		student.setGrade("4");
		student.setName("Drake");
		student.setSchoolName("skiply");
		receipt.setCardNumber("889-008");
		receipt.setCardType("visa");
		receipt.setFee((long) 4000);
		receipt.setStudent(student);
		return receipt;
		
		
	}
}

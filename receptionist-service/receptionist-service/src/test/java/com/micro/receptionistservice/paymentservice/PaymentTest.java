package com.micro.receptionistservice.paymentservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.micro.receptionistservice.services.PaymentServiceImpl;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class PaymentTest {

    PaymentServiceImpl paymentServiceImplMock = mock(PaymentServiceImpl.class);

    @Test
    @DisplayName("Test for addPayment")
    public void addPaymentTest(){
        when(paymentServiceImplMock.addPayment(1, "25-05-2021", "Visa", "1234123412341234", "27-05-2024", 211)).thenReturn("Payment is successful for reservation id "+ 1 + " with payment id: " + 129);
        assertEquals("Payment is successful for reservation id "+ 1 + " with payment id: " + 129,paymentServiceImplMock.addPayment(1, "25-05-2021", "Visa", "1234123412341234", "27-05-2024", 211));
    }
    
}

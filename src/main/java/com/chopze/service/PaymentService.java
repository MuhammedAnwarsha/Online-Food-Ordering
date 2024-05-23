package com.chopze.service;

import com.chopze.model.Order;
import com.chopze.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {

    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}

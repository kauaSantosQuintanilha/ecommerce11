package com.flamengo.ecommerce.respository;

import com.flamengo.ecommerce.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository  extends JpaRepository<Payment, Long> {
}

package com.tc.springmicroservice.repository;

import com.tc.springmicroservice.model.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Integer> {


}

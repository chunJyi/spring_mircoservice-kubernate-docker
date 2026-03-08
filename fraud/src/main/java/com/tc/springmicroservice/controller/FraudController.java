package com.tc.springmicroservice.controller;

import com.tc.springmicroservice.clients.fraud.FraudCheckResponse;
import com.tc.springmicroservice.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check/")
@AllArgsConstructor
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(
            path = "{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public FraudCheckResponse isFraudulentCustomer(@PathVariable("customerId") Integer customerId) {
        log.info("fraud controller", customerId);
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}

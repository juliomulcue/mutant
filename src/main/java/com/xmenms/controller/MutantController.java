package com.xmenms.controller;

import com.xmenms.request.AdnRequest;
import com.xmenms.response.StatsResponse;
import com.xmenms.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.xmenms.constant.Routes.MUTANT;
import static com.xmenms.constant.Routes.STATS;

@RestController
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping(MUTANT)
    public ResponseEntity<String> mutantDetector(@RequestBody AdnRequest adnRequest) {

        HttpStatus httpStatus = HttpStatus.FORBIDDEN;

        if( mutantService.isMutant(adnRequest.getAdn()) ) {
            httpStatus = HttpStatus.OK;
        }

        return new ResponseEntity<>( httpStatus);
    }
    
    @GetMapping(STATS)
    public StatsResponse getStats() {

        return mutantService.getStats();
    }
}

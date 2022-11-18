package com.example.rentabookrestservices.controller;

import com.example.rentabookrestservices.domain.Cancel;
import com.example.rentabookrestservices.service.CancelService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class CancelController {
    private final CancelService cancelService;

    public CancelController(CancelService cancelService) {
        this.cancelService = cancelService;
    }

    @GetMapping("/cancelSales")
    public ResponseEntity<List<Cancel>> getAllCancel(){
        return cancelService.getAllCancel();
    }

    @PostMapping("/cancelSales")
    public ResponseEntity<Cancel> createSaleCancel(@RequestBody Cancel cancel){
        return cancelService.createSaleCancel(cancel);
    }
}

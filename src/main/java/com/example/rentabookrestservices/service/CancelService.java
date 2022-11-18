package com.example.rentabookrestservices.service;

import com.example.rentabookrestservices.domain.Cancel;
import com.example.rentabookrestservices.repository.CancelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancelService {

    private final CancelRepository cancelRepository;

    public CancelService(CancelRepository cancelRepository) {
        this.cancelRepository = cancelRepository;
    }

    public ResponseEntity<List<Cancel>> getAllCancel() {
        List<Cancel> cancelList = cancelRepository.findAll();

        if (cancelList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(cancelList, HttpStatus.OK);
    }


    //TODO cancel eklendi, sonra cancel.cancelType yani Sale/Rent entity'si silinecek
    //Çünkü artık Sale/Rent iptal edilmiş oldu.

    public ResponseEntity<Cancel> createSaleCancel(Cancel cancel) {
        return new ResponseEntity<>(cancelRepository.save(cancel), HttpStatus.CREATED);
    }

}

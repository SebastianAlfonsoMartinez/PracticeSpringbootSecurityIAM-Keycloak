package com.msbills.controller;

import com.msbills.models.Bill;
import com.msbills.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService service;

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/all")
    public ResponseEntity<List<Bill>> getAll() {
        return ResponseEntity.ok().body(service.getAllBill());
    }

    @PreAuthorize("hasAnyAuthority('GROUP_PROVIDERS')")
    @PostMapping("/create")
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        service.createBill(bill);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/findByUser/{id}")
    public ResponseEntity<List<Bill>> searchBillByUserId(@PathVariable("id") String id) {
        return ResponseEntity.ok()
                .body(service.findByUser(id));
    }

    @GetMapping("/assignUser/{customerBill}")
    public ResponseEntity<?> updateRandomCustomerBill(@PathVariable("customerBill") String customerBill) {
        service.updateRandomCustomerBill(customerBill);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

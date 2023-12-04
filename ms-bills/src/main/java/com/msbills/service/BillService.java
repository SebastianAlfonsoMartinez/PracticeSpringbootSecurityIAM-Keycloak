package com.msbills.service;

import com.msbills.models.Bill;
import com.msbills.repositories.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository repository;

    public List<Bill> getAllBill() {
        return repository.findAll();
    }

    public void createBill(Bill bill) {
        repository.save(bill);
    }

    public List<Bill> findByUser(String userId) {
        return repository.findByUser(userId);
    }

    public void updateRandomCustomerBill(String customerBill) {
        List<Bill> allBills = repository.findAll();

        if (!allBills.isEmpty()) {
            int randomIndex = new Random().nextInt(allBills.size());
            Bill randomBill = allBills.get(randomIndex);

            randomBill.setCustomerBill(customerBill);
            repository.save(randomBill);
        }
    }
}

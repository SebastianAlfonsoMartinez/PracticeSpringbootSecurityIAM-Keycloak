package com.example.msusers.repository;

import com.example.msusers.domain.Bill;
import com.example.msusers.security.feing.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "ms-bill", url = "http://localhost:8086", configuration = FeignInterceptor.class)
public interface BillRepositoryFeign {
    @GetMapping("bills/findByUser/{id}")
    List<Bill> findByUser (@PathVariable String id);
    @GetMapping("bills/assignUser/{customerBill}")
    void assignUserToBill(@PathVariable String customerBill);
}

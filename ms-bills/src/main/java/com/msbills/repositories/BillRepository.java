package com.msbills.repositories;

import com.msbills.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, String> {

    @Query("from Bill b where b.customerBill=?1 ")
    List<Bill> findByUser(String userId);

}

package com.coupon_project.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import javax.persistence.LockModeType;

public interface CouponInventoryRepository extends JpaRepository<CouponInventory, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM CouponInventory c WHERE c.id=1")
    CouponInventory countWithPessimisticLock();
}

package com.spunit.OrderManagement.repository;

import com.spunit.OrderManagement.model.Orders;
import com.spunit.UserManagement.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByAuthUser(AuthUser authUser);

}


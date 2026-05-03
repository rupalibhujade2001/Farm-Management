package com.farmmanagement.repository;

import com.farmmanagement.model.FarmAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmAccountRepository extends JpaRepository<FarmAccount, Long> {
}

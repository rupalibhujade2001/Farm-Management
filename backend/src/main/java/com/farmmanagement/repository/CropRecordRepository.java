package com.farmmanagement.repository;

import com.farmmanagement.model.CropRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropRecordRepository extends JpaRepository<CropRecord, Long> {
    List<CropRecord> findByFarmAccountId(Long farmAccountId);
}

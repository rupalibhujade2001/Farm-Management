package com.farmmanagement.service;

import com.farmmanagement.dto.CropRecordRequest;
import com.farmmanagement.dto.FarmAccountRequest;
import com.farmmanagement.model.CropRecord;
import com.farmmanagement.model.FarmAccount;
import com.farmmanagement.repository.CropRecordRepository;
import com.farmmanagement.repository.FarmAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService {

    private final FarmAccountRepository farmAccountRepository;
    private final CropRecordRepository cropRecordRepository;

    public FarmAccount createAccount(FarmAccountRequest request) {
        FarmAccount account = FarmAccount.builder()
                .farmerName(request.farmerName())
                .email(request.email())
                .farmName(request.farmName())
                .location(request.location())
                .build();
        return farmAccountRepository.save(account);
    }

    public List<FarmAccount> getAccounts() {
        return farmAccountRepository.findAll();
    }

    public CropRecord addCropRecord(Long accountId, CropRecordRequest request) {
        FarmAccount account = farmAccountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountId));

        CropRecord record = CropRecord.builder()
                .farmAccount(account)
                .cropName(request.cropName())
                .expense(request.expense())
                .revenue(request.revenue())
                .recordDate(request.recordDate())
                .build();

        return cropRecordRepository.save(record);
    }

    public List<CropRecord> getCropRecords(Long accountId) {
        return cropRecordRepository.findByFarmAccountId(accountId);
    }

    public BigDecimal getTotalProfit(Long accountId) {
        return getCropRecords(accountId).stream()
                .map(r -> r.getRevenue().subtract(r.getExpense()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

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

    public FarmService(FarmAccountRepository farmAccountRepository, CropRecordRepository cropRecordRepository) {
		super();
		this.farmAccountRepository = farmAccountRepository;
		this.cropRecordRepository = cropRecordRepository;
	}

	private final FarmAccountRepository farmAccountRepository;
    private final CropRecordRepository cropRecordRepository;

    public FarmAccount createAccount(FarmAccountRequest request) {
        FarmAccount account = new FarmAccount();
        account.setFarmerName(request.farmerName());
        account.setEmail(request.email());
        account.setFarmName(request.farmName());
        account.setLocation(request.location());
        return farmAccountRepository.save(account);
    }

    public List<FarmAccount> getAccounts() {
        return farmAccountRepository.findAll();
    }

    public CropRecord addCropRecord(Long accountId, CropRecordRequest request) {
        FarmAccount account = farmAccountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountId));

        CropRecord record = new CropRecord();
        record.setFarmAccount(account);
        record.setCropName(request.cropName());
        record.setExpense(request.expense());
        record.setRevenue(request.revenue());
        record.setRecordDate(request.recordDate());

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

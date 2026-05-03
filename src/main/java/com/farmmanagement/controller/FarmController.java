package com.farmmanagement.controller;

import com.farmmanagement.dto.CropRecordRequest;
import com.farmmanagement.dto.FarmAccountRequest;
import com.farmmanagement.model.CropRecord;
import com.farmmanagement.model.FarmAccount;
import com.farmmanagement.service.FarmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/farms")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class FarmController {

    private final FarmService farmService;
    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }
    
    

    @PostMapping
    public FarmAccount createAccount(@Valid @RequestBody FarmAccountRequest request) {
        return farmService.createAccount(request);
    }

    @GetMapping
    public List<FarmAccount> getAccounts() {
        return farmService.getAccounts();
    }

    @PostMapping("/{accountId}/records")
    public CropRecord addCropRecord(@PathVariable Long accountId,
                                    @Valid @RequestBody CropRecordRequest request) {
        return farmService.addCropRecord(accountId, request);
    }

    @GetMapping("/{accountId}/records")
    public List<CropRecord> getCropRecords(@PathVariable Long accountId) {
        return farmService.getCropRecords(accountId);
    }

    @GetMapping("/{accountId}/profit")
    public Map<String, BigDecimal> getTotalProfit(@PathVariable Long accountId) {
        return Map.of("totalProfit", farmService.getTotalProfit(accountId));
    }
}

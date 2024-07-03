package com.example.retailstore.services;

import com.example.retailstore.models.Supply;

import java.util.List;

public interface SupplyService {
    List<Supply> getAllSupplies();

    Supply getSupplyById(Long id);

    Supply saveSupply(Supply supply);

    void deleteSupply(Long id);

    Supply updateSupply(Long id, Supply supply);
}

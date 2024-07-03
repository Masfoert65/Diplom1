package com.example.retailstore.services;

import com.example.retailstore.models.Supply;
import com.example.retailstore.repositories.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplyServiceImpl implements SupplyService {

    private final SupplyRepository supplyRepository;

    @Autowired
    public SupplyServiceImpl(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    @Override
    public List<Supply> getAllSupplies() {
        return supplyRepository.findAll();
    }

    @Override
    public Supply getSupplyById(Long id) {
        Optional<Supply> optionalSupply = supplyRepository.findById(id);
        return optionalSupply.orElse(null);
    }

    @Override
    public Supply saveSupply(Supply supply) {
        return supplyRepository.save(supply);
    }

    @Override
    public void deleteSupply(Long id) {
        supplyRepository.deleteById(id);
    }

    @Override
    public Supply updateSupply(Long id, Supply newSupply) {
        return supplyRepository.findById(id)
                .map(supply -> {
                    supply.setSupplier(newSupply.getSupplier());
                    supply.setSupplyDate(newSupply.getSupplyDate());
                    supply.setTotalCost(newSupply.getTotalCost());
                    return supplyRepository.save(supply);
                }).orElse(null);
    }
}

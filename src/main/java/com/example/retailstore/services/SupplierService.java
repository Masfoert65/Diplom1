package com.example.retailstore.services;

import com.example.retailstore.models.Supplier;
import java.util.List;

public interface SupplierService {
    List<Supplier> getAllSuppliers();
    Supplier getSupplierById(Long id);
    Supplier saveSupplier(Supplier supplier);
    void deleteSupplier(Long id);
}

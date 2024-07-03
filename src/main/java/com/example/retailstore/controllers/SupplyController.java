package com.example.retailstore.controllers;

import com.example.retailstore.models.Supply;
import com.example.retailstore.services.SupplyService;
import com.example.retailstore.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/supplies")
public class SupplyController {

    private final SupplyService supplyService;
    private final SupplierService supplierService;

    @Autowired
    public SupplyController(SupplyService supplyService, SupplierService supplierService) {
        this.supplyService = supplyService;
        this.supplierService = supplierService;
    }

    @GetMapping("/add")
    public String showAddSupplyForm(Model model) {
        model.addAttribute("supplyForm", new Supply());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        model.addAttribute("supplies", supplyService.getAllSupplies());
        return "add_order";
    }

    @PostMapping("/add")
    public String addSupply(@ModelAttribute("supplyForm") Supply supply) {
        supply.setSupplyDate(LocalDateTime.now());
        supplyService.saveSupply(supply);
        return "redirect:/supplies/add";
    }

    @GetMapping("/list")
    public String listSupplies(Model model) {
        List<Supply> supplies = supplyService.getAllSupplies();
        model.addAttribute("supplies", supplies);
        return "supply_list";
    }
}

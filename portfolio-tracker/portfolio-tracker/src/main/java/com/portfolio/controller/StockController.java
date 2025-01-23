package com.portfolio.controller;

import com.portfolio.entity.Stock;
import com.portfolio.service.StockService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/data")
    public List<Stock> getAllStocks() { return stockService.getAllStocks(); }
    
    @Transactional
    @PostMapping("/postdata")
    public Stock addStock(@RequestBody Stock stock) { return stockService.addStock(stock); }

    @PutMapping("update/{id}")
    public Stock updateStock(@PathVariable Long id, @RequestBody Stock stock) {
        return stockService.updateStock(id, stock);
    }

    @DeleteMapping("delete/{id}")
    public void deleteStock(@PathVariable Long id) { stockService.deleteStock(id); }
}

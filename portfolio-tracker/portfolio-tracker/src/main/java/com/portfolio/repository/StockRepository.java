package com.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portfolio.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}



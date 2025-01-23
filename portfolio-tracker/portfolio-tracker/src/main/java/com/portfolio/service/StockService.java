package com.portfolio.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.portfolio.entity.Stock;
import com.portfolio.repository.StockRepository;

import java.util.List;

import org.json.JSONObject;
@Service
public class StockService{
	
	@Autowired
    private StockRepository stockRepo;

public double getStockPrice(String ticker) {
    // API URL with the given ticker symbol and API key
    String apiUrl = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + ticker + "&apikey=YOUR_API_KEY";
    
    // Initialize RestTemplate for making the API call
    RestTemplate restTemplate = new RestTemplate();
    
    // Get the response from the API as a String
    String response = restTemplate.getForObject(apiUrl, String.class);
    
    // Parse the JSON response to extract the stock price
    JSONObject jsonResponse = new JSONObject(response);
    
    // Return the stock price (from the "Global Quote" field)]]
    return jsonResponse.getJSONObject("Global Quote").getDouble("05. price");
    
}

public List<Stock> getAllStocks() {
    return stockRepo.findAll();
}

public Stock addStock(Stock stock) {
    return stockRepo.save(stock);
}

public Stock updateStock(Long id, Stock stockDetails) {
    Stock stock = stockRepo.findById(id).orElseThrow(() -> new RuntimeException("Stock not found"));
    stock.setName(stockDetails.getName());
    stock.setQuantity(stockDetails.getQuantity());
    stock.setBuyPrice(stockDetails.getBuyPrice());
    stock.setTicker(stockDetails.getTicker());
    return stockRepo.save(stock);
}

public void deleteStock(Long id) {
	stockRepo.deleteById(id);
}
}
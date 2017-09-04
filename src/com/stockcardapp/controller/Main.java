package com.stockcardapp.controller;

import com.stockcardapp.model.Stock;
import com.stockcardapp.view.StockView;

public class Main {
	public static void main(String[] args) {
		StockView stockView = new StockView();
		Stock stock = new Stock();
		StockController stockController = new StockController(stock, stockView);
		stockView.setVisible(true);
		stockView.setLocationRelativeTo(null);
	}
}

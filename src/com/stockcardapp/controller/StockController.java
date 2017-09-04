package com.stockcardapp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.stockcardapp.model.Stock;
import com.stockcardapp.view.StockView;

public class StockController {
	private StockView stockView;
	private Stock stock;
	private Stock selectedStock;
	
	public StockController(Stock stock, StockView stockView) {
		this.stock = stock;
		this.stockView = stockView;
		
		this.stockView.addInsertListener(new InsertListener());
		this.stockView.addEditListener(new EditListener());
		this.stockView.addDeleteListener(new DeleteListener());
		this.stockView.addListListener(new ListListener());
		this.stockView.addSearchListener(new SearchListener());
		this.stockView.addListSelectListener(new ListSelectListener());
		
		populateTable();
	}
	
	@SuppressWarnings("serial")
	public void populateTable() {
		Object[] header = { "Stock Code", 
							"Stock Name", 
							"Stock Type",
							"Stock Unit",
							"Barcode",
							"KDV (%)",
							"Date Created",
							"Description" };
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.setColumnIdentifiers(header);
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<Stock> query = builder.createQuery(Stock.class);
		query.from(Stock.class);
		List<Stock> stocks = s.createQuery(query).getResultList();
		for	(Stock item : stocks) {
			Object[] row = new Object[8];
			row[0] = item.getCodeStock();
			row[1] = item.getNameStock();
			row[2] = item.getTypeStock();
			row[3] = item.getUnitStock();
			row[4] = item.getBarcode();
			row[5] = item.getKdv();
			row[6] = item.getDateCreated();
			row[7] = item.getDesc();
			model.addRow(row);
		}
		stockView.setTableModel(model);
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	public void clearForm() {
		stockView.setStockCode("");
		stockView.setStockName("");
		stockView.setStockType("Cotton");
		stockView.setStockUnit("kilogram");
		stockView.setBarcode("");
		stockView.setKdv(1.0f);
		stockView.setDateCreated(new Timestamp(System.currentTimeMillis()));
		stockView.setDescription("");
	}
	
	class InsertListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stock.setCodeStock(stockView.getStockCode());
			stock.setNameStock(stockView.getStockName());
			stock.setTypeStock(stockView.getStockType());
			stock.setUnitStock(stockView.getStockUnit());
			stock.setBarcode(stockView.getBarcode());
			stock.setKdv(stockView.getKdv());
			stock.setDesc(stockView.getDescription());
			stock.setDateCreated(stockView.getDateCreated());
			
			SessionFactory sf = new Configuration().configure().buildSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			s.save(stock);
			s.getTransaction().commit();
			s.close();
			sf.close();
			
			populateTable();
		}
	}
	
	class EditListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			SessionFactory sf = new Configuration().configure().buildSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Stock> query = builder.createQuery(Stock.class);
			Root<Stock> stk = query.from(Stock.class);
			query.where(builder.equal(
					stk.<String>get("codeStock"),
					builder.parameter(String.class, "condition")));
			TypedQuery<Stock> tq = s.createQuery(query);
			tq.setParameter("condition", selectedStock.getCodeStock());
			stock = tq.getSingleResult();
			stock.setCodeStock(stockView.getStockCode());
			stock.setNameStock(stockView.getStockName());
			stock.setTypeStock(stockView.getStockType());
			stock.setUnitStock(stockView.getStockUnit());
			stock.setBarcode(stockView.getBarcode());
			stock.setKdv(stockView.getKdv());
			stock.setDesc(stockView.getDescription());
			stock.setDateCreated(stockView.getDateCreated());
			s.update(stock);
			s.getTransaction().commit();
			s.close();
			sf.close();
			
			populateTable();
		}
	}
	
	class ListListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			populateTable();
			stockView.setKeyword("");
		}
	}
	
	class DeleteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			SessionFactory sf = new Configuration().configure().buildSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Stock> query = builder.createQuery(Stock.class);
			Root<Stock> stk = query.from(Stock.class);
			query.where(builder.equal(
					stk.<String>get("codeStock"),
					builder.parameter(String.class, "condition")));
			TypedQuery<Stock> tq = s.createQuery(query);
			tq.setParameter("condition", selectedStock.getCodeStock());
			stock = tq.getSingleResult();
			s.delete(stock);
			s.getTransaction().commit();
			s.close();
			sf.close();
			
			populateTable();
		}
	}
	
	class SearchListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stockView.clearRowSelection();
			Object[] header = { "Stock Code", 
								"Stock Name", 
								"Stock Type",
								"Stock Unit",
								"Barcode",
								"KDV (%)",
								"Date Created",
								"Description" };
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(header);
			SessionFactory sf = new Configuration().configure().buildSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Stock> query = builder.createQuery(Stock.class);
			Root<Stock> stk = query.from(Stock.class);
			query.where(builder.like(
					stk.<String>get("codeStock"),
					builder.parameter(String.class, "condition")));
			TypedQuery<Stock> tq = s.createQuery(query);
			tq.setParameter("condition", "%"+stockView.getKeyword().toString()+"%");
			List<Stock> stocks = tq.getResultList();
			if (stocks != null) {
				for	(Stock item : stocks) {
					Object[] row = new Object[8];
					row[0] = item.getCodeStock();
					row[1] = item.getNameStock();
					row[2] = item.getTypeStock();
					row[3] = item.getUnitStock();
					row[4] = item.getBarcode();
					row[5] = item.getKdv();
					row[6] = item.getDateCreated();
					row[7] = item.getDesc();
					model.addRow(row);
				}
			}
			stockView.setTableModel(model);
			s.getTransaction().commit();
			s.close();
			sf.close();
			model.fireTableDataChanged();
		}
	}
	
	class ListSelectListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			System.out.println("Selection triggered");
			int row = stockView.getSelectedStockRow();
			int[] col = {0,1,2,3,4,5,6,7};
			
			if (row != -1) {
				selectedStock = new Stock();
				selectedStock.setCodeStock(stockView.getTableValueAt(row, col[0]).toString());
				selectedStock.setNameStock(stockView.getTableValueAt(row, col[1]).toString());
				selectedStock.setTypeStock(stockView.getTableValueAt(row, col[2]).toString());
				selectedStock.setUnitStock(stockView.getTableValueAt(row, col[3]).toString());
				selectedStock.setBarcode(stockView.getTableValueAt(row, col[4]).toString());
				selectedStock.setKdv(Float.parseFloat(stockView.getTableValueAt(row, col[5]).toString()));
				selectedStock.setDateCreated(Timestamp.valueOf(stockView.getTableValueAt(row, col[6]).toString()));
				selectedStock.setDesc(stockView.getTableValueAt(row, col[7]).toString());
				
				stockView.setStockCode(selectedStock.getCodeStock());
				stockView.setStockName(selectedStock.getNameStock());
				stockView.setStockType(selectedStock.getTypeStock());
				stockView.setStockUnit(selectedStock.getUnitStock());
				stockView.setBarcode(selectedStock.getBarcode());
				stockView.setKdv(selectedStock.getKdv());
				stockView.setDateCreated(selectedStock.getDateCreated());
				stockView.setDescription(selectedStock.getDesc());
			} else {
				selectedStock = null;
				clearForm();
			}
		}
		
	}
	
}

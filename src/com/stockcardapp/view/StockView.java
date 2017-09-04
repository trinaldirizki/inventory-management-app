package com.stockcardapp.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class StockView extends JFrame {

	private static JPanel contentPane;
	private static JTextField txtCode;
	private static JTextField txtName;
	private static JTextField txtBarcode;
	private static JTable tableStock;
	private static JTextField txtSearch;
	private static JComboBox<String> cbxType;
	private static JComboBox<String> cbxUnit;
	private static JComboBox<String> cbxKdv;
	private static JFormattedTextField txtDate;
	private static JTextArea txtDesc;
	
	private static JButton btnInsert;
	private static JButton btnEdit;
	private static JButton btnDelete;
	private static JButton btnList;
	private static JButton btnSearch;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockView frame = new StockView();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StockView() {
		setTitle("Stock Card App");
		setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelForm = new JPanel();
		panelForm.setBackground(Color.GRAY);
		panelForm.setBounds(10, 11, 304, 669);
		contentPane.add(panelForm);
		panelForm.setLayout(null);
		
		JLabel lblCode = new JLabel("Stock Code");
		lblCode.setForeground(Color.WHITE);
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCode.setBounds(10, 11, 84, 14);
		panelForm.add(lblCode);
		
		txtCode = new JTextField();
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCode.setBounds(8, 34, 286, 26);
		panelForm.add(txtCode);
		txtCode.setColumns(10);
		
		JLabel lblName = new JLabel("Stock Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(10, 71, 84, 14);
		panelForm.add(lblName);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtName.setColumns(10);
		txtName.setBounds(8, 94, 286, 26);
		panelForm.add(txtName);
		
		JLabel lblType = new JLabel("Stock Type");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblType.setBounds(10, 131, 84, 14);
		panelForm.add(lblType);
		
		JLabel lblUnit = new JLabel("Stock Unit");
		lblUnit.setForeground(Color.WHITE);
		lblUnit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUnit.setBounds(10, 191, 84, 14);
		panelForm.add(lblUnit);
		
		JLabel lblBarcode = new JLabel("Barcode");
		lblBarcode.setForeground(Color.WHITE);
		lblBarcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBarcode.setBounds(10, 251, 84, 14);
		panelForm.add(lblBarcode);
		
		txtBarcode = new JTextField();
		txtBarcode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtBarcode.setColumns(10);
		txtBarcode.setBounds(8, 274, 286, 26);
		panelForm.add(txtBarcode);
		
		JLabel lblKdv = new JLabel("KDV (%)");
		lblKdv.setForeground(Color.WHITE);
		lblKdv.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKdv.setBounds(10, 311, 84, 14);
		panelForm.add(lblKdv);
		
		JLabel lblDate = new JLabel("Date Created");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDate.setBounds(10, 371, 98, 14);
		panelForm.add(lblDate);
		
		JLabel lblDescription = new JLabel("Description (optional)");
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescription.setBounds(10, 431, 160, 14);
		panelForm.add(lblDescription);
		
		btnInsert = new JButton("Insert Stock");
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInsert.setBounds(10, 552, 284, 29);
		panelForm.add(btnInsert);
		
		btnEdit = new JButton("Update Stock");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEdit.setBounds(10, 592, 284, 29);
		panelForm.add(btnEdit);
		
		btnDelete = new JButton("Delete Stock");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(10, 632, 284, 29);
		panelForm.add(btnDelete);
		
		cbxType = new JComboBox<>();
		cbxType.setModel(new DefaultComboBoxModel<String>(new String[] {"Cotton", "Silk", "Linen", "Wool", "Leather", "Nylon", "Denim"}));
		cbxType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxType.setBounds(8, 154, 286, 26);
		panelForm.add(cbxType);
		
		cbxUnit = new JComboBox<>();
		cbxUnit.setModel(new DefaultComboBoxModel<String>(new String[] {"kilogram", "metre", "centimetre"}));
		cbxUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxUnit.setBounds(8, 214, 286, 26);
		panelForm.add(cbxUnit);
		
		cbxKdv = new JComboBox<>();
		cbxKdv.setModel(new DefaultComboBoxModel<String>(new String[] {"1.0", "8.0", "18.0"}));
		cbxKdv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxKdv.setBounds(8, 336, 286, 26);
		panelForm.add(cbxKdv);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		txtDate = new JFormattedTextField(format);
		txtDate.setEditable(false);
		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDate.setBounds(8, 394, 286, 26);
		txtDate.setValue(new Timestamp(System.currentTimeMillis()));
		panelForm.add(txtDate);
		
		txtDesc = new JTextArea();
		txtDesc.setLineWrap(true);
		txtDesc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDesc.setBounds(10, 456, 335, 85);
		
		JScrollPane scrollDesc = new JScrollPane(txtDesc);
		scrollDesc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollDesc.setBounds(10, 456, 284, 85);
		panelForm.add(scrollDesc);
		
		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.LIGHT_GRAY);
		panelTable.setBounds(324, 11, 940, 669);
		contentPane.add(panelTable);
		panelTable.setLayout(null);
		
		btnList = new JButton("List Stocks");
		btnList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnList.setBounds(10, 33, 128, 29);
		panelTable.add(btnList);
		
		btnSearch = new JButton("Search by Stock Code");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(739, 33, 191, 29);
		panelTable.add(btnSearch);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSearch.setColumns(10);
		txtSearch.setBounds(352, 33, 377, 29);
		panelTable.add(txtSearch);
		
		tableStock = new JTable();
		tableStock.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableStock.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "", "", "", "", "", "", null},
			},
			new String[] {
				"Stock Code", "Stock Name", "Stock Type", "Stock Unit", "Barcode", "KDV (%)", "Date Created", "Description"
			}
		));
		tableStock.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableStock.setBounds(10, 73, 869, 585);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 920, 585);
		scrollPane.setViewportView(tableStock);
		panelTable.add(scrollPane);
	}
	
	public String getStockCode() {
		return txtCode.getText().toString();
	}
	
	public void setStockCode(String s) {
		txtCode.setText(s);
	}
	
	public String getStockName() {
		return txtName.getText().toString();
	}
	
	public void setStockName(String s) {
		txtName.setText(s);
	}
	
	public String getStockType() {
		return cbxType.getSelectedItem().toString();
	}
	
	public void setStockType(String s) {
		cbxType.setSelectedItem(s);
	}
	
	public String getStockUnit() {
		return cbxUnit.getSelectedItem().toString();
	}
	
	public void setStockUnit(String s) {
		cbxUnit.setSelectedItem(s);
	}
	
	public String getBarcode() {
		return txtBarcode.getText().toString();
	}
	
	public void setBarcode(String s) {
		txtBarcode.setText(s);
	}
	
	public float getKdv() {
		return Float.parseFloat(cbxKdv.getSelectedItem().toString());
	}
	
	public void setKdv(float f) {
		cbxKdv.setSelectedItem(String.valueOf(f));
	}
	
	public Timestamp getDateCreated() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void setDateCreated(Timestamp t) {
		txtDate.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(t));
	}
	
	public String getDescription() {
		return txtDesc.getText().toString();
	}
	
	public void setDescription(String s) {
		txtDesc.setText(s);
	}
	
	public String getKeyword() {
		return txtSearch.getText().toString();
	}
	
	public void setKeyword(String s) {
		txtSearch.setText(s);
	}
	
	public TableModel getTableModel() {
		return tableStock.getModel();
	}
	
	public void setTableModel(TableModel model) {
		tableStock.setModel(model);
	}
	
	public void setTableRowSorter(TableRowSorter<TableModel> sorter) {
		tableStock.setRowSorter(sorter);
	}
	
	public int getSelectedStockRow() {
		return tableStock.getSelectedRow();
	}
	
	public Object getTableValueAt(int row, int col) {
		return tableStock.getValueAt(row, col);
	}
	
	public void clearRowSelection() {
		tableStock.clearSelection();
	}
	
	public void addInsertListener(ActionListener listener) {
		btnInsert.addActionListener(listener);
	}
	
	public void addEditListener(ActionListener listener) {
		btnEdit.addActionListener(listener);
	}
	
	public void addDeleteListener(ActionListener listener) {
		btnDelete.addActionListener(listener);
	}
	
	public void addListListener(ActionListener listener) {
		btnList.addActionListener(listener);
	}
	
	public void addSearchListener(ActionListener listener) {
		btnSearch.addActionListener(listener);
	}
	
	public void addListSelectListener(ListSelectionListener listener) {
		tableStock.getSelectionModel().addListSelectionListener(listener);
	}
}

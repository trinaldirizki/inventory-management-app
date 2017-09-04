package com.stockcardapp.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity @Table(name="stock")
public class Stock implements Serializable {
	
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="code_stock")
	private String codeStock;
	@Column(name="name_stock")
	private String nameStock;
	@Column(name="type_stock")
	private String typeStock;
	@Column(name="unit_stock")
	private String unitStock;
	@Column(name="barcode")
	private String barcode;
	@Column(name="kdv")
	private float kdv;
	@Column(name="description")
	private String desc;
	@Column(name="date_created")
	private Timestamp dateCreated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeStock() {
		return codeStock;
	}

	public void setCodeStock(String codeStock) {
		this.codeStock = codeStock;
	}

	public String getNameStock() {
		return nameStock;
	}

	public void setNameStock(String nameStock) {
		this.nameStock = nameStock;
	}

	public String getTypeStock() {
		return typeStock;
	}

	public void setTypeStock(String typeStock) {
		this.typeStock = typeStock;
	}

	public String getUnitStock() {
		return unitStock;
	}

	public void setUnitStock(String unitStock) {
		this.unitStock = unitStock;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public float getKdv() {
		return kdv;
	}

	public void setKdv(float kdv) {
		this.kdv = kdv;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
}

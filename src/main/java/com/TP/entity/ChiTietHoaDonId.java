package com.TP.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ChiTietHoaDonId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int mahoadon;
	int machitietsanpham;

	public int getMahoadon() {
		return mahoadon;
	}

	public void setMahoadon(int mahoadon) {
		this.mahoadon = mahoadon;
	}

	public int getMachitietsanpham() {
		return machitietsanpham;
	}

	public void setMachitietsanpham(int machitietsanpham) {
		this.machitietsanpham = machitietsanpham;
	}
}

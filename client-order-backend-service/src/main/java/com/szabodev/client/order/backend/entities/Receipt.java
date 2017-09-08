package com.szabodev.client.order.backend.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author zszabo
 */
@Entity
@Table(name = "receipt")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Receipt.findAll", query = "SELECT r FROM Receipt r"),
		@NamedQuery(name = "Receipt.findByReceiptid", query = "SELECT r FROM Receipt r WHERE r.receiptid = :receiptid") })
public class Receipt implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "RECEIPTID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer receiptid;

	@Lob
	@Column(name = "RECEIPTFILE")
	private byte[] receiptfile;

	@Size(max = 255)
	@Column(name = "FILENAME")
	private String filename;

	@JoinColumn(name = "ORDERID", referencedColumnName = "ORDERSID")
	@ManyToOne
	private Orders orderid;

	public Receipt() {
	}

	public Receipt(Integer receiptid) {
		this.receiptid = receiptid;
	}

	public Integer getReceiptid() {
		return receiptid;
	}

	public void setReceiptid(Integer receiptid) {
		this.receiptid = receiptid;
	}

	public byte[] getReceiptfile() {
		return receiptfile;
	}

	public void setReceiptfile(byte[] receiptfile) {
		this.receiptfile = receiptfile;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Orders getOrderid() {
		return orderid;
	}

	public void setOrderid(Orders orderid) {
		this.orderid = orderid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (receiptid != null ? receiptid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Receipt)) {
			return false;
		}
		Receipt other = (Receipt) object;
		if ((this.receiptid == null && other.receiptid != null) || (this.receiptid != null && !this.receiptid.equals(other.receiptid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return filename;
	}

}
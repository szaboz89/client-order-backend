package com.szabodev.client.order.backend.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author zszabo
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"), @NamedQuery(name = "Orders.findByOrdersid", query = "SELECT o FROM Orders o WHERE o.ordersid = :ordersid"),
		@NamedQuery(name = "Orders.findByComment", query = "SELECT o FROM Orders o WHERE o.comment = :comment") })
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "ordersid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ordersid;

	@Size(max = 255)
	@Column(name = "comment")
	private String comment;

	@JoinColumn(name = "clientid", referencedColumnName = "CLIENTID")
	@ManyToOne
	private Client clientid;

	@JoinColumn(name = "productid", referencedColumnName = "productid")
	@ManyToOne
	private Product productid;

	@OneToMany(mappedBy = "orderid")
	private Collection<Receipt> receiptCollection;

	public Orders() {
	}

	public Orders(Integer ordersid) {
		this.ordersid = ordersid;
	}

	public Integer getOrdersid() {
		return ordersid;
	}

	public void setOrdersid(Integer ordersid) {
		this.ordersid = ordersid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Client getClientid() {
		return clientid;
	}

	public void setClientid(Client clientid) {
		this.clientid = clientid;
	}

	public Product getProductid() {
		return productid;
	}

	public void setProductid(Product productid) {
		this.productid = productid;
	}

	@XmlTransient
	public Collection<Receipt> getReceiptCollection() {
		return receiptCollection;
	}

	public void setReceiptCollection(Collection<Receipt> receiptCollection) {
		this.receiptCollection = receiptCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (ordersid != null ? ordersid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Orders)) {
			return false;
		}
		Orders other = (Orders) object;
		if ((this.ordersid == null && other.ordersid != null) || (this.ordersid != null && !this.ordersid.equals(other.ordersid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entities.Orders[ ordersid=" + ordersid + " ]";
	}

	public void addReceipt(Receipt newreceipt) {
		newreceipt.setOrderid(this);
		getReceiptCollection().add(newreceipt);
	}

}
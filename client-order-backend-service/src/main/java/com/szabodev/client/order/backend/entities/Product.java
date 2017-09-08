package com.szabodev.client.order.backend.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "product")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
		@NamedQuery(name = "Product.findByProductid", query = "SELECT p FROM Product p WHERE p.productid = :productid"),
		@NamedQuery(name = "Product.findByProductname", query = "SELECT p FROM Product p WHERE p.productname = :productname"),
		@NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
		@NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description") })
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "productid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productid;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "productname")
	private String productname;

	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "price")
	private Double price;

	@Size(max = 255)
	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "productid")
	private Collection<Request> requestCollection;

	@OneToMany(mappedBy = "productid")
	private Collection<Orders> ordersCollection;

	public Product() {
	}

	public Product(Integer productid) {
		this.productid = productid;
	}

	public Product(Integer productid, String productname) {
		this.productid = productid;
		this.productname = productname;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlTransient
	public Collection<Request> getRequestCollection() {
		return requestCollection;
	}

	public void setRequestCollection(Collection<Request> requestCollection) {
		this.requestCollection = requestCollection;
	}

	@XmlTransient
	public Collection<Orders> getOrdersCollection() {
		return ordersCollection;
	}

	public void setOrdersCollection(Collection<Orders> ordersCollection) {
		this.ordersCollection = ordersCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (productid != null ? productid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Product)) {
			return false;
		}
		Product other = (Product) object;
		if ((this.productid == null && other.productid != null) || (this.productid != null && !this.productid.equals(other.productid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entities.Product[ productid=" + productid + " ]";
	}

	public void addOrder(Orders order) {
		order.setProductid(this);
		getOrdersCollection().add(order);
	}

	public void addRequest(Request request) {
		request.setProductid(this);
		getRequestCollection().add(request);
	}

}

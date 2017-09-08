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

@Entity
@Table(name = "client")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"), @NamedQuery(name = "Client.findByClientid", query = "SELECT c FROM Client c WHERE c.clientid = :clientid"),
		@NamedQuery(name = "Client.findByName", query = "SELECT c FROM Client c WHERE c.name = :name"),
		@NamedQuery(name = "Client.findByAddress", query = "SELECT c FROM Client c WHERE c.address = :address") })
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "CLIENTID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer clientid;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "NAME")
	private String name;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "ADDRESS")
	private String address;

	@OneToMany(mappedBy = "clientid")
	private Collection<Request> requestCollection;

	@OneToMany(mappedBy = "clientid")
	private Collection<Orders> ordersCollection;

	public Client() {
	}

	public Client(Integer clientid) {
		this.clientid = clientid;
	}

	public Client(Integer clientid, String name, String address) {
		this.clientid = clientid;
		this.name = name;
		this.address = address;
	}

	public Integer getClientid() {
		return clientid;
	}

	public void setClientid(Integer clientid) {
		this.clientid = clientid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		hash += (clientid != null ? clientid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Client)) {
			return false;
		}
		Client other = (Client) object;
		if ((this.clientid == null && other.clientid != null) || (this.clientid != null && !this.clientid.equals(other.clientid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	public void addOrder(Orders order) {
		order.setClientid(this);
		getOrdersCollection().add(order);
	}

	public void addRequest(Request request) {
		request.setClientid(this);
		getRequestCollection().add(request);
	}

}

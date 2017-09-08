package com.szabodev.client.order.backend.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zszabo
 */
@Entity
@Table(name = "request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r"),
    @NamedQuery(name = "Request.findByRequestid", query = "SELECT r FROM Request r WHERE r.requestid = :requestid"),
    @NamedQuery(name = "Request.findByComment", query = "SELECT r FROM Request r WHERE r.comment = :comment")})
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "requestid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer requestid;
    @Size(max = 255)
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "clientid", referencedColumnName = "CLIENTID")
    @ManyToOne
    private Client clientid;
    @JoinColumn(name = "productid", referencedColumnName = "productid")
    @ManyToOne
    private Product productid;

    public Request() {
    }

    public Request(Integer requestid) {
        this.requestid = requestid;
    }

    public Integer getRequestid() {
        return requestid;
    }

    public void setRequestid(Integer requestid) {
        this.requestid = requestid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestid != null ? requestid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.requestid == null && other.requestid != null) || (this.requestid != null && !this.requestid.equals(other.requestid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Request[ requestid=" + requestid + " ]";
    }
    
}
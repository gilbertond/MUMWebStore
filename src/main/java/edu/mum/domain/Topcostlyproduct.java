/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "topcostlyproduct")
public class Topcostlyproduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportid;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dategenerated")
    private Date dategenerated;
    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;
    @Column(name = "currentdateprice")
    private Double currentdateprice;

    public Topcostlyproduct() {
    }

    public Topcostlyproduct(Date dategenerated, Product product, Double currentdateprice) {
        this.dategenerated = dategenerated;
        this.product = product;
        this.currentdateprice = currentdateprice;
    }

    public Integer getReportid() {
        return reportid;
    }

    public void setReportid(Integer reportid) {
        this.reportid = reportid;
    }

    public Date getDategenerated() {
        return dategenerated;
    }

    public void setDategenerated(Date dategenerated) {
        this.dategenerated = dategenerated;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getCurrentdateprice() {
        return currentdateprice;
    }

    public void setCurrentdateprice(Double currentdateprice) {
        this.currentdateprice = currentdateprice;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + Objects.hashCode(this.reportid);
        hash = 31 * hash + Objects.hashCode(this.dategenerated);
        hash = 31 * hash + Objects.hashCode(this.product);
        hash = 31 * hash + Objects.hashCode(this.currentdateprice);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Topcostlyproduct other = (Topcostlyproduct) obj;
        if (!Objects.equals(this.reportid, other.getReportid())) {
            return false;
        }
        if (!Objects.equals(this.dategenerated, other.getCurrentdateprice())) {
            return false;
        }
        if (!Objects.equals(this.product, other.getProduct())) {
            return false;
        }
        if (!Objects.equals(this.currentdateprice, other.getCurrentdateprice())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Topcostlyproduct{" + "reportid=" + reportid + ", dategenerated=" + dategenerated + ", product=" + product.getProductName() + ", currentdateprice=" + currentdateprice + '}';
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ndenzi Gilbert
 */
@Entity
@Table(name = "autoactivityrunsetting")
public class Autoactivityrunsetting implements Serializable {

    @Column(name = "starttime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date starttime;
    @Column(name = "endtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endtime;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autoactivityrunsettingid", nullable = false)
    private Integer autoactivityrunsettingid;
    @Column(name = "activityname")
    private String activityname;
    @Column(name = "description")
    private String description;
    @Column(name = "added")
    private Boolean added;
    @Column(name = "beanname")
    private String beanname;
    @OneToMany(mappedBy = "autoactivityrunsetting")
    private List<Services> autoactivityrunsettingList; 
    
    public Autoactivityrunsetting() {
    }

    public Autoactivityrunsetting(Integer autoactivityrunsettingid) {
        this.autoactivityrunsettingid = autoactivityrunsettingid;
    }

    public Autoactivityrunsetting(String activityname, String description, String beanname) {
        this.activityname = activityname;
        this.description = description;
        this.beanname = beanname;
    }

    public List<Services> getAutoactivityrunsettingList() {
        return autoactivityrunsettingList;
    }

    public void setAutoactivityrunsettingList(List<Services> autoactivityrunsettingList) {
        this.autoactivityrunsettingList = autoactivityrunsettingList;
    }
    
    public String getDescription() {
        return description;
    }

    public String getBeanname() {
        return beanname;
    }

    public void setBeanname(String beanname) {
        this.beanname = beanname;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAutoactivityrunsettingid() {
        return autoactivityrunsettingid;
    }

    public Boolean getAdded() {
        return added;
    }

    public void setAdded(Boolean added) {
        this.added = added;
    }

    public void setAutoactivityrunsettingid(Integer autoactivityrunsettingid) {
        this.autoactivityrunsettingid = autoactivityrunsettingid;
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (autoactivityrunsettingid != null ? autoactivityrunsettingid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autoactivityrunsetting)) {
            return false;
        }
        Autoactivityrunsetting other = (Autoactivityrunsetting) object;
        if ((this.autoactivityrunsettingid == null && other.autoactivityrunsettingid != null) || (this.autoactivityrunsettingid != null && !this.autoactivityrunsettingid.equals(other.autoactivityrunsettingid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iics.domain.Autoactivityrunsetting[ autoactivityrunsettingid=" + autoactivityrunsettingid + " ]";
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
    private static final Logger LOG = Logger.getLogger(Autoactivityrunsetting.class.getName());

}

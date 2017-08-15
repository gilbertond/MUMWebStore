/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.domain;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.*;
import java.util.Date;
/**
 *
 * @author Gilbert Ndenzi
 */
@Entity
@Table(name = "services")
public class Services implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "serviceid")
    private Integer serviceid;
    @Basic(optional = false)
    @Column(name = "servicename")
    private String servicename;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "description")
    private String description;
    @Column(name = "crondescription")
    private String crondescription;
    @Column(name = "frequency")
    private String frequency;
    @Basic(optional = true)
    @Column(name = "freqvalue")
    private String freqvalue;
    @Basic(optional = true)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startingtime")
    private Date startingtime;
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datecreated")
    private Date datecreated;
    @Column(name = "endingtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endingtime;
    @Column(name = "startingtimepattern")
    private String startingtimepattern;
    @Column(name = "startonstartup")
    private Boolean startonstartup;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datechanged")
    private Date datechanged;
    @Column(name = "lastruntime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastruntime;
    @Column(name = "nextruntime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextruntime;
    @Column(name = "startondemand")
    private Boolean startondemand;
    @Column(name = "completed")
    private Boolean completed;
    @JoinColumn(name = "autoactivityrunsetting", referencedColumnName = "autoactivityrunsettingid")
    @ManyToOne
    private Autoactivityrunsetting autoactivityrunsetting;
    @Column(name = "interrupted")
    private Boolean interrupted;

    public Services() {
    }

    public Services(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public Boolean getInterrupted() {
        return interrupted;
    }

    public void setInterrupted(Boolean interrupted) {
        this.interrupted = interrupted;
    }
    
    public String getCrondescription() {
        return crondescription;
    }

    public void setCrondescription(String crondescription) {
        this.crondescription = crondescription;
    }        

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }        

    public Autoactivityrunsetting getAutoactivityrunsetting() {
        return autoactivityrunsetting;
    }

    public void setAutoactivityrunsetting(Autoactivityrunsetting autoactivityrunsetting) {
        this.autoactivityrunsetting = autoactivityrunsetting;
    }
    
    public Services(Integer serviceid, String servicename, String freqvalue, Date startingtime) {
        this.serviceid = serviceid;
        this.servicename = servicename;
        this.freqvalue = freqvalue;
        this.startingtime = startingtime;
    }

    public Integer getServiceid() {
        return serviceid;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public String getStartingtimepattern() {
        return startingtimepattern;
    }

    public void setStartingtimepattern(String startingtimepattern) {
        this.startingtimepattern = startingtimepattern;
    }

    public Boolean getStartonstartup() {
        return startonstartup;
    }

    public void setStartonstartup(Boolean startonstartup) {
        this.startonstartup = startonstartup;
    }

    public Date getDatechanged() {
        return datechanged;
    }

    public void setDatechanged(Date datechanged) {
        this.datechanged = datechanged;
    }
    
    public void setServiceid(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getFreqvalue() {
        return freqvalue;
    }

    public void setFreqvalue(String freqvalue) {
        this.freqvalue = freqvalue;
    }

    public Date getStartingtime() {
        return startingtime;
    }

    public void setStartingtime(Date startingtime) {
        this.startingtime = startingtime;
    }

    public Date getEndingtime() {
        return endingtime;
    }

    public void setEndingtime(Date endingtime) {
        this.endingtime = endingtime;
    }

    public Date getLastruntime() {
        return lastruntime;
    }

    public void setLastruntime(Date lastruntime) {
        this.lastruntime = lastruntime;
    }

    public Date getNextruntime() {
        return nextruntime;
    }

    public void setNextruntime(Date nextruntime) {
        this.nextruntime = nextruntime;
    }

    public Boolean getStartondemand() {
        return startondemand;
    }

    public void setStartondemand(Boolean startondemand) {
        this.startondemand = startondemand;
    }

//    public Collection<Servicestatuslog> getServicestatuslogCollection() {
//        return servicestatuslogCollection;
//    }
//
//    public void setServicestatuslogCollection(Collection<Servicestatuslog> servicestatuslogCollection) {
//        this.servicestatuslogCollection = servicestatuslogCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceid != null ? serviceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Services)) {
            return false;
        }
        Services other = (Services) object;
        if ((this.serviceid == null && other.serviceid != null) || (this.serviceid != null && !this.serviceid.equals(other.serviceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iics.domain.Services[serviceid=" + serviceid + "]";
    }
    private static final Logger LOG = Logger.getLogger(Services.class.getName());

}

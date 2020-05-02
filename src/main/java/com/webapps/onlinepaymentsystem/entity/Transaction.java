/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 *
 * @author marks
 */
@Entity
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date timestamp;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User fromUser;
    
    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    private User toUser;
  
    @NotNull
    private String description;
    
    @NotNull
    @DecimalMin("0.0")
    private float sendAmount;
   
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Currency sendCurrency;
   
    @NotNull
    @DecimalMin("0.0")
    private float recvAmount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Currency recvCurrency;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSendAmount() {
        return sendAmount;
    }

    public void setSendAmount(float sendAmount) {
        this.sendAmount = sendAmount;
    }

    public Currency getSendCurrency() {
        return sendCurrency;
    }

    public void setSendCurrency(Currency sendCurrency) {
        this.sendCurrency = sendCurrency;
    }

    public float getRecvAmount() {
        return recvAmount;
    }

    public void setRecvAmount(float recvAmount) {
        this.recvAmount = recvAmount;
    }

    public Currency getRecvCurrency() {
        return recvCurrency;
    }

    public void setRecvCurrency(Currency recvCurrency) {
        this.recvCurrency = recvCurrency;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.timestamp);
        hash = 13 * hash + Objects.hashCode(this.fromUser);
        hash = 13 * hash + Objects.hashCode(this.toUser);
        hash = 13 * hash + Objects.hashCode(this.description);
        hash = 13 * hash + Float.floatToIntBits(this.sendAmount);
        hash = 13 * hash + Objects.hashCode(this.sendCurrency);
        hash = 13 * hash + Float.floatToIntBits(this.recvAmount);
        hash = 13 * hash + Objects.hashCode(this.recvCurrency);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transaction other = (Transaction) obj;
        if (Float.floatToIntBits(this.sendAmount) != Float.floatToIntBits(other.sendAmount)) {
            return false;
        }
        if (Float.floatToIntBits(this.recvAmount) != Float.floatToIntBits(other.recvAmount)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        if (!Objects.equals(this.fromUser, other.fromUser)) {
            return false;
        }
        if (!Objects.equals(this.toUser, other.toUser)) {
            return false;
        }
        if (!Objects.equals(this.sendCurrency, other.sendCurrency)) {
            return false;
        }
        if (!Objects.equals(this.recvCurrency, other.recvCurrency)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.webapps.onlinepaymentsystem.entity.Transaction{" + "id=" + id + ", timestamp=" + timestamp + ", fromUser=" + fromUser + ", toUser=" + toUser + ", description=" + description + ", sendAmount=" + sendAmount + ", sendCurrency=" + sendCurrency + ", recvAmount=" + recvAmount + ", recvCurrency=" + recvCurrency + '}';
    }

    
}

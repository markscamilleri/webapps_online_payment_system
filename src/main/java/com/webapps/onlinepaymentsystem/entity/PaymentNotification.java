/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.entity;

import com.webapps.onlinepaymentsystem.enums.TriStateBoolean;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

/**
 * This represents a request a user sends to another user 
 * asking for payment of a specified amount
 * 
 */
@Entity
public class PaymentNotification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @PastOrPresent
    private Date txTimestamp;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User requestingUser;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User payer;

    @NotNull
    private String description;

    @NotNull
    @DecimalMin("0.00")
    private float amount;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Currency currency;
    
    // TriState boolean, null means hasn't taken action yet.
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private TriStateBoolean isAccepted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return txTimestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.txTimestamp = timestamp;
    }

    public User getRequestingUser() {
        return requestingUser;
    }

    public void setRequestingUser(User requestingUser) {
        this.requestingUser = requestingUser;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public TriStateBoolean isIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(TriStateBoolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.txTimestamp);
        hash = 67 * hash + Objects.hashCode(this.requestingUser);
        hash = 67 * hash + Objects.hashCode(this.payer);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Float.floatToIntBits(this.amount);
        hash = 67 * hash + Objects.hashCode(this.currency);
        hash = 67 * hash + Objects.hashCode(this.isAccepted);
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
        final PaymentNotification other = (PaymentNotification) obj;
        if (Float.floatToIntBits(this.amount) != Float.floatToIntBits(other.amount)) {
            return false;
        }
        
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.txTimestamp, other.txTimestamp)) {
            return false;
        }
        if (!Objects.equals(this.requestingUser, other.requestingUser)) {
            return false;
        }
        if (!Objects.equals(this.payer, other.payer)) {
            return false;
        }
        if (this.isAccepted != other.isAccepted) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.webapps.onlinepaymentsystem.entity.PaymentNotification{" + "id=" + id + ", timestamp=" + txTimestamp + ", requestingUser=" + requestingUser + ", payer=" + payer + ", description=" + description + ", amount=" + amount + ", currency=" + currency + ", isAccepted=" + isAccepted + '}';
    }           
    
}

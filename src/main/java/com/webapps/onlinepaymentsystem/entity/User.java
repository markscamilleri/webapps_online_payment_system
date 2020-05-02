/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

/**
 *
 *
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    @DecimalMin("0.00")
    private float balance;

    @NotNull
    @ManyToOne
    private Currency currency;

    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @PastOrPresent
    private Date registrationTimestamp;

    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @PastOrPresent
    private Date lastLogin;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fromUser")
    private List<Transaction> transactionsOut;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toUser")
    private List<Transaction> transactionsIn;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "requestingUser")
    private List<PaymentNotification> notificationsSent;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "payer")
    private List<PaymentNotification> notificationsRecv;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    public void setRegistrationTimestamp(Date registrationTimestamp) {
        this.registrationTimestamp = registrationTimestamp;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    public List<Transaction> getTransactionsOut() {
        return transactionsOut;
    }

    public void setTransactionsOut(List<Transaction> transactionsOut) {
        this.transactionsOut = transactionsOut;
    }

    public List<Transaction> getTransactionsIn() {
        return transactionsIn;
    }

    public void setTransactionsIn(List<Transaction> transactionsIn) {
        this.transactionsIn = transactionsIn;
    }

    public List<PaymentNotification> getNotificationsSent() {
        return notificationsSent;
    }

    public void setNotificationsSent(List<PaymentNotification> notificationsSent) {
        this.notificationsSent = notificationsSent;
    }

    public List<PaymentNotification> getNotificationsRecv() {
        return notificationsRecv;
    }

    public void setNotificationsRecv(List<PaymentNotification> notificationsRecv) {
        this.notificationsRecv = notificationsRecv;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + Float.floatToIntBits(this.balance);
        hash = 97 * hash + Objects.hashCode(this.currency);
        hash = 97 * hash + Objects.hashCode(this.registrationTimestamp);
        hash = 97 * hash + Objects.hashCode(this.lastLogin);
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
        final User other = (User) obj;
        if (Float.floatToIntBits(this.balance) != Float.floatToIntBits(other.balance)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        if (!Objects.equals(this.registrationTimestamp, other.registrationTimestamp)) {
            return false;
        }
        if (!Objects.equals(this.lastLogin, other.lastLogin)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.webapps.onlinepaymentsystem.entity.User{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", balance=" + balance + ", currency=" + currency + ", registrationTimestamp=" + registrationTimestamp + ", lastLogin=" + lastLogin + '}';
    }

}

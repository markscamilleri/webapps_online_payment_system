/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import java.time.LocalDateTime;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author marks
 */
@Named(value = "timeClientBean")
@ApplicationScoped
public class TimeClientBean implements TimeClientService {

    /**
     * Creates a new instance of TimeClientBean
     */
    public TimeClientBean() {
    }
    
    public LocalDateTime getTimeWithoutThrift(){
        return LocalDateTime.now();
    }

    @Override
    public LocalDateTime getTimeFromTimeServer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

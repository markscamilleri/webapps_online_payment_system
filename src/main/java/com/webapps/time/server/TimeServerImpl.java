/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.time.server;

import com.webapps.tserver.gen.*;
import org.apache.thrift.TException;

class TimeServerServiceHandler implements TimeServer.Iface {

    @Override
    public long time() throws TException {
        long time = System.currentTimeMillis();
        return time;
    }
}

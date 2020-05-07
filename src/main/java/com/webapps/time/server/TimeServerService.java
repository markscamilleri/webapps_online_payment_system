/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.time.server;

import com.webapps.tserver.gen.TimeServer;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Init;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class TimeServerService {
    
    @Init
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void run() {
        try {
            TServerSocket serverTransport = new TServerSocket(10000);
            TimeServer.Processor processor = new TimeServer.Processor(new TimeServerServiceHandler());
            Factory protFactory = new TBinaryProtocol.Factory(true, true);
            
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
            args.processor(processor);
            args.protocolFactory(protFactory);
            
            TServer server = new TThreadPoolServer(args);
            System.out.println("Starting server on port 10000 ...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}

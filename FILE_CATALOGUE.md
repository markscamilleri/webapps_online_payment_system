# Web Apps 2020 Assignment
## Online Payment System

### nb-configuration.xml
This is the netbeans configuration file

### pom.xml
Maven dependency management configuration for this project

### thrift-0.13.0.exe
The thrift executable used to generate the thrift time server

### src/thrift
 - time.thrift: The thrift code to generate the time server

### src/main/java/com/webapps/conversion
 - CurrencyConversion.java: The object representing the JSON/XML response this REST server gives.
 - CurrencyConversionResource.java: The source for the RESTful server to convert currencies.
 - JAXRSConfiguration.java: Application configuration class for the RESTful server.

### src/main/java/com/webapps/onlinepaymentsystem
 - JAXRSConfiguration.java: Application configuration class for the webapp.

#### src/main/java/com/webapps/onlinepaymentsystem/dao

#### src/main/java/com/webapps/onlinepaymentsystem/dto

#### src/main/java/com/webapps/onlinepaymentsystem/ejb

#### src/main/java/com/webapps/onlinepaymentsystem/entity

#### src/main/java/com/webapps/onlinepaymentsystem/enums

#### src/main/java/com/webapps/onlinepaymentsystem/exceptions

#### src/main/java/com/webapps/onlinepaymentsystem/http

#### src/main/java/com/webapps/onlinepaymentsystem/jsf

#### src/main/java/com/webapps/onlinepaymentsystem/logging

### src/main/java/com/webapps/time/server
  - TimeServerBean.java: Bean to try deploy the time server as a bean (I understand this might not be working)
  - TimeServerService.java: Code to set up the time server to respond to port 10000 requests
  - TimeServerImpl.java: Code to respond to the requests and serve the time.

### src/main/java/com/webapps/tserver/gen
  - TimeServer.java: Thrift generated code.

### src/resources

### src/webapp

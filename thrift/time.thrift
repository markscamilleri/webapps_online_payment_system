# time.thrift
namespace java com.webapps.tserver.gen
typedef i64 Timestamp
service TimeServer {
   Timestamp time()
}
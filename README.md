##Example application for "Building a Cassandra Application from Scratch" talk at Cassandra 2012

This is a simple example application to demonstrate some of the techiques of creating a Cassandra based application. This example is written in Java and uses the Hector library for connectivity.

##Database setup

To setup the database, run the creation script using the cassandra-cli using the following format: 

cassandra-cli -host localhost -port 9160 -f create-database-cli.txt


##Stubbed methods

There are a few methods that have been stubbed-out for you to implement if you would like to try your hand at Cassandra application code. If you just want to see the completed code, a solutions branch will be made available soon.

The stubbed methods are:

getUser
setVideoWithUserIndex
getComments
getRating
getVideoLastStopEvent
getCommentsOnTimeSlice

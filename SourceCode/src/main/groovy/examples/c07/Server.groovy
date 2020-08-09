package examples.c07

// copyright 2012-18 Jon Kerridge
// Using Concurrency and Parallelism Effectively parts i & ii, 2014, bookboon.com

import jcsp.lang.*
import groovyJCSP.*


class Server implements CSProcess{
	  
  def ChannelInput clientRequest
  def ChannelOutput clientSend  
  def ChannelOutput thisServerRequest
  def ChannelInput thisServerReceive  
  def ChannelInput otherServerRequest
  def ChannelOutput otherServerSend  
  def dataMap = [ : ]
  def serverNumber

  void run () {
    def CLIENT = 0
    def OTHER_REQUEST = 1
    def THIS_RECEIVE = 2
    def serverAlt = new ALT ([clientRequest, 
		                      otherServerRequest, 
							  thisServerReceive])
    while (true) {
      def index = serverAlt.select()

      switch (index) {		  
        case CLIENT :
          def key = clientRequest.read()
          //println "server $serverNumber request  = ${key}"
          if ( dataMap.containsKey(key) ) {
            clientSend.write(dataMap[key])
            //println "datamap contains key and it was written to client"
          }
          else{
            thisServerRequest.write(key)
            //println "This server $serverNumber don't have key and asks other server"
          }
          //end if
          break
        case OTHER_REQUEST :
          def key = otherServerRequest.read()
          //println "server $serverNumber read from other server  = ${key}"
          if ( dataMap.containsKey(key) ) {
            otherServerSend.write(dataMap[key])
            //println "other server contains key and it was written to client(previous server $serverNumber)"
          }
          else {
            otherServerSend.write(-1)
            //println "Other server do not have this key"
          }//end if
          break
        case THIS_RECEIVE :
          clientSend.write(thisServerReceive.read() )
          //println "this server $serverNumber received value and sends it back to the client"
          break
      } // end switch              
    } //end while   
  } //end run
}

package exercises.c4

import jcsp.lang.*
import groovyJCSP.*
import groovyJCSP.plugAndPlay.*


class ResetNumbers implements CSProcess {
  
  def ChannelOutput outChannel
  def ChannelInput resetChannel
  def int initialValue = 0
  
  void run() {
    
    One2OneChannel a = Channel.createOne2One()
    One2OneChannel b = Channel.createOne2One()
    One2OneChannel c = Channel.createOne2One()
    
    def testList = [ new GPrefix ( prefixValue: initialValue, 
                                   outChannel: a.out(), 
                                   inChannel: c.in() ),
                     new GPCopy ( inChannel: a.in(), 
                            	  outChannel0: outChannel, 
                            	  outChannel1: b.out() ),
                     // requires a constructor for ResetSuccessor
                      new ResetSuccessor (
                                inChannel: b.in(),
                              outChannel: c.out(),
                              resetChannel: resetChannel


                      )
                  ]
    new PAR ( testList ).run()    
  }
}
                         

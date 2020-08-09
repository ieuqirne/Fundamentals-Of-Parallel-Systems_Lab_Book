package exercises.c4


import jcsp.lang.*
import groovyJCSP.*

class ResetSuccessor implements CSProcess {
	  
  def ChannelOutput outChannel
  def ChannelInput  inChannel
  def ChannelInput  resetChannel
	  
  void run () {
    def guards = [ resetChannel, inChannel  ]
    def alt = new ALT ( guards )
      while (true) {
          def index = alt.priSelect()
          if (index == 0 ) {    // resetChannel input
              // deal with inputs from resteChannel and inChannel
              def resetValue = resetChannel.read()
              resetChannel.read()
              outChannel.write(resetValue)
          }
          // use a priSelect
          else {    //inChannel input
              outChannel.write(inChannel.read()+1)
          }
      }

  }
}

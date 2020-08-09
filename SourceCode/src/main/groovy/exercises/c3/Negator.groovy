package exercises.c3

import jcsp.lang.*

class Negator implements CSProcess {
  
  def ChannelInput inChannel
  def ChannelOutput outChannel
  
  void run () {
    while (true) {
      def num = inChannel.read()
      outChannel.write(-num )
      //output the negative of the input value
    }
  }
}

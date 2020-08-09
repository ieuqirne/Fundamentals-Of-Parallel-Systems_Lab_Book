package exercises.c2

import jcsp.lang.*
 
class Multiplier implements CSProcess {
  
  def ChannelOutput outChannel
  def ChannelInput inChannel
  def int factor = 2
  
  void run() {
    def i = inChannel.read()
    while (i > 0) {
      outChannel.write(i * factor)  // write i * factor to outChannel
      i = inChannel.read()   // read in the next value of i
    }
    outChannel.write(i)
  }
}

    

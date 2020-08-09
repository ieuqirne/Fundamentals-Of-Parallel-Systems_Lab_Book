package exercises.c2

import jcsp.lang.*

class CreateSetsOfEight implements CSProcess{
	
	def ChannelInput inChannel

	void run(){
		def outList = []
		def v = inChannel.read()
		while (v != -1){
			for ( i in 0 .. 7 ) {
				outList.add(v)
				v = inChannel.read()
				// put v into outList and read next input
			}
			println " Eight Object is ${outList}"
			outList = []
		}
		println "Finished"
	}
}
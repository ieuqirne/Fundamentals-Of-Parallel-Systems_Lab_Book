package examples.c09

import jcsp.lang.*

class CountingMissing implements CSProcess{
    boolean passed = true
    def ChannelInput inChannel
    def ChannelOutput outChannel
    def prev

    void run () {
        def e = inChannel.read()
        prev =  e.data
        while (true) {
            prev = e.data
            e = inChannel.read()
            if (e.data != 100 && e.data != prev + e.missed +1) {
                passed = false
                println "Incorrect"
            }

            outChannel.write(e)

        }
    }
}

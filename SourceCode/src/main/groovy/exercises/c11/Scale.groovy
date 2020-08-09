package exercises.c11

import groovyJCSP.ALT
import jcsp.lang.CSProcess
import jcsp.lang.CSTimer
import jcsp.lang.ChannelInput
import jcsp.lang.ChannelOutput

class Scale implements CSProcess {

    def int scaling = 2
    def int multiplier = 2

    def ChannelOutput outChannel
    def ChannelOutput factor
    def ChannelInput inChannel
    def ChannelInput suspend
    def ChannelInput injector

    void run () {
        def SECOND = 1000
        def DOUBLE_INTERVAL = 5 * SECOND
        def NORMAL_SUSPEND  = 0
        def NORMAL_TIMER    = 1
        def NORMAL_IN       = 2
        def SUSPENDED_INJECT = 0
        def SUSPENDED_IN     = 1
        def timer = new CSTimer()
        def normalAlt = new ALT ( [ suspend, timer, inChannel ] )
        def suspendedAlt = new ALT ( [ injector, inChannel ] )
        def timeout = timer.read() + DOUBLE_INTERVAL
        timer.setAlarm ( timeout )

        outChannel.write( "Original                 Scaled\n" )

        while (true) {
            switch ( normalAlt.priSelect() ) {

                case NORMAL_SUSPEND :
                    suspend.read()
                    factor.write(scaling)
                    def suspended = true
                    outChannel.write( "Suspended\n" )
                    while ( suspended ) {

                        switch ( suspendedAlt.priSelect() ) {

                            case SUSPENDED_INJECT:
                                scaling = injector.read()
                                outChannel.write( "Injected scaling is $scaling\n" )
                                suspended = false
                                timeout = timer.read() + DOUBLE_INTERVAL
                                timer.setAlarm ( timeout )
                                break

                            case SUSPENDED_IN:
                                def inValue = inChannel.read()
                                def result = new ScaledData()
                                result.original = inValue
                                result.scaled = inValue
                                outChannel.write ( result.toString() + "\n" )
                                break
                        }  // end-switch
                    } //end-while
                    break

                case NORMAL_TIMER:
                    timeout = timer.read() + DOUBLE_INTERVAL
                    timer.setAlarm ( timeout )
                    scaling = scaling * multiplier
                    outChannel.write( "Normal Timer: new scaling is $scaling\n")
                    break

                case NORMAL_IN:
                    def inValue = inChannel.read()
                    def result = new ScaledData()
                    result.original = inValue
                    result.scaled = inValue * scaling
                    outChannel.write ( result.toString() + "\n" )
                    break

            } //end-switch
        } //end-while
    } //end-run
} // end Scalecale
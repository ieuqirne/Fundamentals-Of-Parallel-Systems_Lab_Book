package exercises.c11

import groovyJCSP.ALT
import jcsp.lang.*

class ControllerManager implements CSProcess {

    def ChannelInput print

    def ChannelInput fromScale
    def ChannelOutput toScaleSuspend
    def ChannelOutput toScaleInject
    def int START_SCALE
    def ChannelInput buttons
    def ChannelOutput toUILabel
    def ChannelOutput toUISuspend

    void run() {
        def scale = START_SCALE
        toUILabel.write( scale.toString() )


        while (true) {

            def buttonDoing = buttons.read() // Read buttonDoing from UI
            if (buttonDoing == "Suspend Scaler") {
                toUISuspend.write("Scaler is suspended") // Change button to suspended when clicked
                toScaleSuspend.write(0)// Suspend Scale process
                scale = fromScale.read() // Send scaler and send it to Interface
                toUILabel.write(scale.toString())
                def integerScale = false// Wait till buttonDoing is an integer value
                while (!integerScale) {
                    buttonDoing = buttons.read()
                    try {
                        buttonDoing = Integer.parseInt(buttonDoing)// Get new scale value from buttonDoing
                        scale = buttonDoing
                        integerScale = true
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong number");
                    }
                }
                toUISuspend.write("Suspend Scaler")// Change button back to suspend
                toUILabel.write(scale.toString())
                toScaleInject.write(scale)
            }

        }
    }
}
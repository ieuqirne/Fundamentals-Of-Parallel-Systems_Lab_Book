package exercises.c11


import groovyJCSP.PAR
import jcsp.awt.*
import jcsp.lang.CSProcess
import jcsp.lang.ChannelInput
import jcsp.lang.ChannelOutput

import java.awt.*

class UserInterface implements CSProcess {

    def ActiveCanvas controllerCanvas
    def int canvasSize
    def ChannelInput scaleValueConfig
    def ChannelInput suspendButtonConfig
    def ChannelInput printValueConfig
    def ChannelOutput buttonEvent


    void run() {
        def root = new ActiveClosingFrame ("Scaling Controller")
        def mainFrame = root.getActiveFrame()

        def gprint = new ActiveTextArea(printValueConfig, null) // Printing Current Values
        def suspendButton = new ActiveButton(suspendButtonConfig, buttonEvent, "Suspend Scaler")// Suspend button
        def newScaleLabel = new Label("Enter New Scale")// New scale
        newScaleLabel.setAlignment(Label.CENTER)
        def newScale = new ActiveTextEnterField(null, buttonEvent) //Getting new factor
        Panel newScalePanel = new Panel (new GridLayout (2, 2))
        newScalePanel.add (newScale.getActiveTextField ())

        def scaleValue = new ActiveLabel (scaleValueConfig)// Printing new scaler with label
        scaleValue.setAlignment(Label.LEFT)
        def scaleLabel = new Label ("Current Scale ")
        scaleLabel.setAlignment(Label.RIGHT)

        def printCont = new Container() // Setting up container
        printCont.setLayout(new GridLayout (1, 1))
        printCont.add(gprint)
        def currentCont = new Container()
        currentCont.setLayout(new GridLayout (1, 1))
        currentCont.add(scaleLabel)
        currentCont.add(scaleValue)
        def newCont = new Container()
        newCont.setLayout(new GridLayout (2, 1))
        newCont.add(newScaleLabel)
        newCont.add(newScalePanel)
        def susCont = new Container()
        susCont.setLayout(new GridLayout (1, 1))
        susCont.add(suspendButton)

        mainFrame.setLayout(new BorderLayout())
        mainFrame.add(printCont, BorderLayout.CENTER)
        mainFrame.add(currentCont, BorderLayout.NORTH)
        mainFrame.add(newCont, BorderLayout.EAST)
        mainFrame.add(susCont, BorderLayout.SOUTH)

        mainFrame.pack()
        mainFrame.setVisible(true)

        def network = [ root, controllerCanvas, gprint, scaleValue, newScale, suspendButton]
        new PAR (network).run()

    }
}
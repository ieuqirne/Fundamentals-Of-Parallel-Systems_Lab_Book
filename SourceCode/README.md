The UCaPE repository contains the sources of all the examples and exercises
used in the book "Using Concurrency and Parallelism Effectively parts i & ii"
by Jon Kerridge published by Bookboon, free of charge.

http://bookboon.com/en/using-concurrency-and-parallelism-effectively-i-ebook
http://bookboon.com/en/using-concurrency-and-parallelism-effectively-ii-ebook

In order to use these source files you will need to put them into your IDE, which
will need Apache Groovy enabled( http://groovy-lang.org/download.html ).

You will need to ensure that the java and groovy versions of the libraries match
with the versions you are using in your IDE.

It should be noted that the exercises packages import code from the examples package
and this will need to be maintained in your IDE.

The line numbers shown in the code samples in the books will line up with the line 
numbers in your IDE.

A test folder is included called sanityCheck, which will be executed as part of the Gradle build.  
If the test passes the build will complete with no further message but if not a message
will be produced.  The sanityCheck can be run by itself by following:
src> test> groovy> sanityCheck> TestProducerConsumer, which is a runnable script.

A further simple test to ensure that the software has been installed correctly is:
navigate from src> main> groovy> examples> c02> RunHelloWorld.groovy, which is a runnable script.

Use the IDE to run the script and 'Hello World!' should appear in the console.

In Chapters 15 onwards, contained in part ii of the book, examples of the use of 
networked systems is presented.  Some of these chapter contain two packages one 
labelled net and the other called net2.  The net2 packages are the ones described 
in the book and require no further intervention except to run the main Groovy scripts
as described in the text.

The net package examples require the running of the CNS-Server **_before_** any 
of the other components are invoked.  Each package has a pdf containing a description,
which came from an earlier version of the books, which was not actually published.  
This was because the net2 became available and has more functionality, especially 
where mobility of processes was concerned.

The CNS-Server is found in jcsp.net.tcpip.TCPIPCNSServer within the jcsp-v.m.p.jar
library and can be run directly from the library in your IDE.
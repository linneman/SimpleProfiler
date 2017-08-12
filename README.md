# SimpleProfiler

Java Utility Functions for Analyzing the Stack Frames of JVM Threads

## Object

Java application on  embedded devices are specifically prone to  bottle necks in
terms of memory consumption and  runtime performance. This can become especially
an issue when integrating 3rd party  software which was originally developed for
a JVM running on powerful host  systems (mostly intel x86_64). Typical profiling
tools to further investigate performance bootle necks such as VisualVM might not
be  available for  the  target virtual  machine  or simply  come  with too  much
overhead.

In majoriy  of the  case the  memory foot  print of  a complex  Java application
cannot be  easily reduced. So Memory  issues are mostly addressed  by giving the
JVM just enough heap via its boot arguments.

Many  embedded  application  e.g.  for  the internet  of  things  (IOT)  can  be
considered to  be I/O  bound. The  most critical  runtime perfomance  measure is
often system start up  time. However, we currently face a  situation where a 3rd
pary library consumes  nearly 100% of the  application processor's computational
power for periods of  several minutes. It is suspected that this  is caused by a
poor  implementation somewhere  in the  given  library in  example when  polling
variables very frequently. We hope to be able to identify these by observing the
call stack in all active threads periodically. Luckily the Java Standard Library
allows  to  retrieve  this  information  from  the  class  Thread.  This  sample
application illustrates how to do this.

## Running Test Code

In order to compile and run test code invoke ant from command line:

    ant

and execute the build artfect via

    java -jar dist/lib/TestSimpleProfiler-<build-date>.jar

This  writes the  stack trace  of all  treads  running in  the JVM  to the  file
'jvm_stack_traces.log' located in your home directory.

## How to include the Profiler in your Java Application

To profile your own application include the following lines to it:

````Java
import SimpleProfiler;
// ...
yourFunction() {
  // ...
  SimpleProfiler mySimpleProfiler = new SimpleProfiler(logFileName, 5000 );
  mySimpleProfiler.run();
````

Refer to the file [SimpleProfilerTest.java](tree/master/src/SimpleProfilerTest.java) for more detailed information.

## Licence

This software stands under the terms of the
[GNU Lesser General Public Licence v2](https://www.gnu.org/licenses/old-licenses/lgpl-2.1).

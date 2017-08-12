/*
 * Java Utility Functions for Analyzing the Stack Frames of JVM Threads
 *
 * The use and distribution terms for this software are covered by
 * the GNU Lesser General Public License v2.1
 *
 * (C) 2017, Otto Linnemann
 */

package SimpleProfiler;
import java.io.*;
import java.util.*;

/**
 *   Implementation of light weight JVM Analyzer Functions
 *
 *   Refer to README.md or https://github.com/linneman/SimpleProfiler.git
 *   for more detailed explanation.
 *
 * @author Otto Linnemann
 * @version %I%, %G%
 * @since JDK1.0
 */
public class SimpleProfiler implements Runnable
{
  private int invocation_period = 5000;
  private PrintStream out;

  /**  Constructor for analyzer functions
   *
   *   @param logFileName a full qualified file name for the log output data
   *   @param invocation_period logging period in milliseconds
   */
  public SimpleProfiler( String logFileName, int invocation_period ) throws FileNotFoundException {
    this.invocation_period = invocation_period;
    out = new PrintStream(new File(logFileName));
  }

  private void printStack( StackTraceElement[] elements )  {
    for( int i=0; i<elements.length ; ++i ) {
      out.println("\t"+elements[i].toString());
    }
  }

  /**  print out the current stack frame for all JVM threads */
  public void printThreads() {
    Map<Thread,StackTraceElement[]> m = Thread.getAllStackTraces();
    Iterator it = m.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();
      it.remove(); // https://stackoverflow.com/a/1066603: avoids a ConcurrentModificationException
      out.println( pair.getKey() );
      printStack( (StackTraceElement[])pair.getValue() );
      out.println();
    }
  }

  /** starts the timer controller invocation of printThreads */
  public void run() {
    Timer timer = new Timer();
    timer.schedule( new TimerTask() {
        @Override
        public void run () {
          Date date = new Date();
          out.println( "Active Threads and Stack Traces at " + date.toString() );
          out.println( "================================================================");
          printThreads();
          out.println();
        }
      }, 0, invocation_period );
  }
}

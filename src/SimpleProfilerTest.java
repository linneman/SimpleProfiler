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

/**
* @author Otto Linnemann
* @version %I%, %G%
* @since JDK1.0
*/

public class SimpleProfilerTest
{
  public static void main( String[] args ) {
    final String logFileName = System.getProperty("user.home") + File.separator + "jvm_stack_traces.log";
    try {
      SimpleProfiler mySimpleProfiler = new SimpleProfiler(logFileName, 5000 );

      mySimpleProfiler.run();
      System.out.println( "Java profiling is running, view file " + logFileName + " for its output!" );

    } catch( FileNotFoundException e ) {
      System.err.println( "Could not open log file " + logFileName + " for writing error!" );
    }
  }
}

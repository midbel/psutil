package org.midbel.psutil;

import java.util.ArrayList;
import java.util.Collections;
import org.midbel.proc.Process;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Process ps = new Process();
        try {
            ArrayList<Process.ProcInfo> list = ps.all();
            Collections.sort(list, (Process.ProcInfo fst, Process.ProcInfo snd) -> {
                return fst.pid() - snd.pid();
            });
            for (Process.ProcInfo info: ps.all()) {
                System.out.format("process: %d: %s %s - %d:%d\n", info.pid(), info.name(), info.status(), info.uid(), info.gid());
            }
        } catch (Exception e) {
            System.err.println("unexpected error reading process information");
            System.err.println(e);
        }
    }
}

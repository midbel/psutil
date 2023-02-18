package org.midbel.psutil;

import static spark.Spark.*;
import org.midbel.proc.Process;
import org.midbel.proc.Uptime;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        port(8081);
        get("/uptime", (request, response) -> {
            Uptime up = new Uptime();
            return up.up();
        }, new GsonTransformer());
        get("/boottime", (request, response) -> {
            Uptime up = new Uptime();
            return up.boot().toString();
        }, new GsonTransformer());
        get("/process", (requesst, response) -> {
            Process ps = new Process();
            return ps.all();
        }, new GsonTransformer());
    }
}

package org.midbel.psutil;

import org.midbel.proc.Uptime;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Uptime up = new Uptime();
        try {
            System.out.format("system boot at %s", up.boot().toString());
            System.out.println();
        } catch (Exception e) {
            System.err.println("oups something bad happens!");
        }
    }
}

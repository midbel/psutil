package org.midbel.proc;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class Uptime {
    public long up() throws IOException {
        return read();
    }

    public LocalDateTime boot() throws IOException {
        long sec = read();
        LocalDateTime now = LocalDateTime.now();
        return now.minusSeconds(sec);
    }

    private long read() throws IOException {
        try (BufferedReader buf = Files.newBufferedReader(Paths.get("/proc/uptime"))) {
            String line = buf.readLine();
            line = line.substring(0, line.indexOf(" ", 0));
            return (long)Float.parseFloat(line);
        }
    }
}

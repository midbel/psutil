package org.midbel.proc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.util.ArrayList;

public class Process {

    public record ProcInfo(int pid, String name, String status, int uid, int gid) {}

    public ArrayList<ProcInfo> all() throws IOException {
        return read();
    }

    private ArrayList<ProcInfo> read() throws IOException {
        DirectoryStream.Filter<Path> filter = (Path entry) -> {
            try {
                Integer.parseInt(entry.getFileName().toString());
                return true;
            } catch(Exception e) {
                return false;
            }
        };
        DirectoryStream<Path> all = Files.newDirectoryStream(Paths.get("/proc"), filter);
        ArrayList<ProcInfo> list = new ArrayList<>();
        for (Path entry: all) {
            list.add(make(entry));
        }
        return list;
    }

    private ProcInfo make(Path dir) throws IOException {
        try (BufferedReader buf = Files.newBufferedReader(dir.resolve("status"))) {
            String line;
            String name = "";
            String status = "";
            int pid = 0;
            int gid = 0;
            int uid = 0;
            while ((line = buf.readLine()) != null) {
                String field = line.substring(0, line.indexOf(":"));
                String value = line.substring(line.indexOf(":")+1);
                switch (field.toLowerCase()) {
                    case "name":
                    name = value.strip();
                    break;
                    case "pid":
                    pid = Integer.parseInt(value.strip());
                    break;
                    case "uid":
                    value = value.strip();
                    uid = Integer.parseInt(value.substring(0, value.indexOf("\t")));
                    break;
                    case "gid":
                    value = value.strip();
                    gid = Integer.parseInt(value.substring(0, value.indexOf("\t")));
                    break;
                    case "state":
                    status = value.strip();
                    break;
                }
            }
            return new ProcInfo(pid, name, status, uid, gid);
        }
    }
}

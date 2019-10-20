package core;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

public class Connections {

    List<TreeSet<Station>> connections = new ArrayList<>();

    public void addConnection(TreeSet<Station> stations)
    {
        boolean founded = false;
        for (TreeSet<Station> ls : connections) {
           if (ls.equals(stations)) {
               founded = true;
           }
        }
        if (!founded) {
            connections.add(stations);
        }
    }

    public List<TreeSet<Station>> getConnections() {
        return connections;
    }
}

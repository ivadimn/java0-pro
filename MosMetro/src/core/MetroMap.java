package core;

import java.util.List;
import java.util.TreeMap;

public class MetroMap {

    private TreeMap<String, Line> lines;
    private Connections connections;
    private List<Station> stations;

    public MetroMap() {
        //
    }

    public TreeMap<String, Line> getLines() {
        return lines;
    }

    public void setLines(TreeMap<String, Line> lines) {
        this.lines = lines;
    }

    public Connections getConnections() {
        return connections;
    }

    public void setConnections(Connections connections) {
        this.connections = connections;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}

package core;

import java.util.ArrayList;
import java.util.List;

public class Line implements Comparable<Line>
{
    private double number;
    private String originaNumber;
    private String name;
    private List<Station> stations;

    public Line(double number, String name, String originaNumber)
    {
        this.originaNumber = originaNumber;
        this.number = number;
        this.name = name;
        stations = new ArrayList<>();
    }

    public double getNumber()
    {
        return number;
    }

    public String getOriginaNumber() {
        return originaNumber;
    }

    public String getName()
    {
        return name;
    }

    public void addStation(Station station)
    {
        stations.add(station);
    }

    public List<Station> getStations()
    {
        return stations;
    }

    @Override
    public int compareTo(Line line)
    {
        return Double.compare(number, line.getNumber());
    }

    @Override
    public boolean equals(Object obj)
    {
        return compareTo((Line) obj) == 0;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", number, name);
    }
}
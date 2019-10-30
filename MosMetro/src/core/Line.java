package core;

import java.util.ArrayList;
import java.util.List;

public class Line implements Comparable<Line>
{
    private String number;
    private String name;
    private List<Station> stations;

    public Line(String number, String name)
    {
        this.number = number;
        this.name = name;
        stations = new ArrayList<>();
    }

    public String getNumber() {
        return number;
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
        double num1 = getDoubleNumber(number);
        double num2 = getDoubleNumber(line.getNumber());
        return Double.compare(num1, num2);
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

    private double getDoubleNumber(String n) {
        String clear = n.replaceAll("\\D", "");
        return n.length() == clear.length() ? Double.valueOf(clear) : Double.valueOf(clear) + 0.5;
    }
}
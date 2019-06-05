package core;

public class Car
{
    //переменная типа String
    public String number;
    //переменная типа int
    public int height;
    //переменная типа double
    public double weight;
    //переменная типа boolean
    public boolean hasVehicle;
    //переменная типа boolean
    public boolean isSpecial;

    public String toString()
    {
        //переменная типа String
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }
}
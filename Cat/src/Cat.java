
public class Cat
{

    public static final double MIN_WEIGTH = 1000.0;
    public static final double MAX_WEIGTH = 9000.0;
    public static final int EYES_COUNT = 2;


    private static Integer count = 0;
    private Double originWeight;
    private Double weight;


    private Double massFeed = 0.0;
    private String status = "Playing";
    private String name;

    private ColorCat color;

    public static Integer getCatCount() {
        return count;
    }

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        count++;
    }

    public Cat(Double weight)
    {
        this.weight = weight;
        originWeight = weight;
        count++;
    }

    public Cat(Double weight, Double originWeight)
    {
        this(weight);
        this.originWeight = originWeight;
        count++;
    }

    public Cat(Double weight, Double originWeight, String name)
    {
        this(weight, originWeight);
        this.name = name;
        count++;
    }

    public ColorCat getColor() {
        return color;
    }

    public void setColor(ColorCat color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cat createATwin() {
        Cat clone = null;
        if (isLive()) {
            clone = new Cat(this.weight, this.originWeight, this.name);
            clone.massFeed = this.massFeed;
            clone.status = this.status;
            clone.color = this.color;

        }
        else {
            System.out.println(" Из трупа клон не создать ...");
        }
        return clone;
    }

    public void meow()
    {
        if (isLive()) {
            weight = weight - 1;
            setStatus();
            System.out.println("Meow");
        }
        else {
            System.out.println(" Труп не мяучит ...");
        }

    }

    public void gotoWC() {
        if (isLive()) {
            Double wc = 100.0 * Math.random();
            weight = weight - wc;
            setStatus();
            System.out.println("Go to WC weight wc - " + wc);
        }
        else {
            System.out.println(" Труп не ходит в туалет ...");
        }
    }

    public Double getMassFeed() {
        return massFeed;
    }
    public void feed(Double amount)
    {
        if (isLive()) {
            weight = weight + amount;
            massFeed = massFeed + amount;
            setStatus();
        }
        else {
            System.out.println(" Труп ничего не ест ...");
        }

    }

    public void drink(Double amount)
    {
        if (isLive()) {
            weight = weight + amount;
            massFeed = massFeed + amount;
            setStatus();
        }
        else {
            System.out.println(" Труп ничего не пьёт ...");
        }
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus() {
        return status;
    }

    public boolean isLive() {
        return status.equals("Playing") || status.equals("Sleeping") ? true : false;
    }

    private void setStatus()
    {
        if(weight < MIN_WEIGTH) {
            count--;
            status = "Dead";
        }
        else if(weight > MAX_WEIGTH) {
            count--;
            status = "Exploded";
        }
        else if(weight > originWeight) {
            status = "Sleeping";
        }
        else {
            status = "Playing";
        }
    }
}
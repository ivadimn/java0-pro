public class Loader
{
    public static void main(String[] args)
    {
        int catsCount = 5;
        Cat[] cats = new Cat[catsCount];
        for (int i = 0; i < catsCount; i++) {
            cats[i] = new Cat();
            System.out.println("Weight cat " + i + " - " + cats[i].getWeight());
        }

        System.out.println("Количество кошек - " + Cat.getCatCount());

        System.out.println("Накормим и напоем ...");
        for (int i = 0; i < catsCount; i++) {
            cats[i].feed(5000 * Math.random());
            cats[i].drink(5000 * Math.random());
            System.out.println("Cat " + i + " Weight - " + cats[i].getWeight() + " and Status - " + cats[i].getStatus());
        }
        System.out.println("-----------------------------------------------------");


        System.out.println("Сколько съели и выпили...");
        for (int i = 0; i < catsCount; i++) {
             System.out.println("Cat " + i + " Mass feed - " + cats[i].getMassFeed());
        }
        System.out.println("-----------------------------------------------------");

        System.out.println("Идём в туалет ...");
        for (int i = 0; i < catsCount; i++) {
            cats[i].gotoWC();
            System.out.println("Cat " + i + " Weight - " + cats[i].getWeight() + " and Status - " + cats[i].getStatus());
        }

        System.out.println("-----------------------------------------------------");

        System.out.println("Мяучим ... ");
        for (int i = 0; i < catsCount; i++) {
            if (cats[i].isLive()) {
                System.out.println("Мяучим кошку " + i);
                for (int j = 0; j < 7000; j++) {
                    cats[i].meow();
                }
                System.out.println(" После мяучинья Cat " + i + " Weight - " + cats[i].getWeight() + " Status - " + cats[i].getStatus());
                break;
            }
        }
        System.out.println("-----------------------------------------------------");

        System.out.println("Клонируем кошку ...");
        Cat clone = cats[0].createATwin();
        System.out.println(" cats[0] weight - " + cats[0].getWeight());
        if (clone != null)
            System.out.println(" clone weight " + clone.getWeight());


        System.out.println("Создаём котёнка ...");
        Cat kitten = createCat();
        System.out.println(" kittewn weight " + kitten.getWeight());



        System.out.println("Количество кошек - " + Cat.getCatCount());
    }

    public static Cat createCat() {
        return new Cat(100 + 100 * Math.random());
    }
}
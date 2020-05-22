import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IllegalArgumentException("Не достаточное количество аргументов комады 'add'\n" +
                    "правильный формат команды 'add': add Василий Петров" +
                    " vasily.petrov@gmail.com +79215637722");
        }
        if (!checkEmail(components[2])) {
            throw new IllegalArgumentException("Не правильно введён адрес электронной почты\n " +
                    " правильный формат : vasily.petrov@gmail.com");
        }
        if (!checkPhoneNumber(components[3])) {
            throw new IllegalArgumentException("Не правильно введён номер телефона\n " +
                    " правильный формат : +79215637722");
        }
        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers()
    {
        if (storage.isEmpty()) {
            throw new RuntimeException("Список контактов пустой");
        }
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        if (storage.isEmpty()) {
            throw new RuntimeException("Список контактов пустой");
        }
        String[] components = name.split("\\s+");
        if (components.length != 2) {
            throw new IllegalArgumentException("Не достаточное количество аргументов комады 'remove'\n" +
                    "правильный формат команды 'remove': remove Василий Петров");
        }
        if (!storage.containsKey(name)) {
            throw new IllegalArgumentException("Контакт с именем " + name + " отсутствует в списке");
        }
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }

    private boolean checkEmail(String email) {
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    private boolean checkPhoneNumber(String number) {
        Pattern p = Pattern.compile("^\\+[0-9]{11}$");
        Matcher m = p.matcher(number);
        return m.matches();
    }
}
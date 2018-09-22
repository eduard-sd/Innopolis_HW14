import ru.kuznetsov.VendingMachine;
import ru.kuznetsov.drinks.DrinkType;
import ru.kuznetsov.exceptions.CrumpledMoney;
import ru.kuznetsov.exceptions.NoMoneyException;
import ru.kuznetsov.exceptions.NoSuchButtonException;
import ru.kuznetsov.exceptions.QuantityException;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static VendingMachine vm = new VendingMachine();


    public static void main(String[] args) throws IOException {
        LogManager.getLogManager().readConfiguration();
        Handler fileHandler = new FileHandler();
        LOGGER.addHandler(fileHandler);
        LOGGER.setUseParentHandlers(false); // как поменять у вейдинг машины настройки по умолчанию? и почему так много файлов создается при логировании?

        System.out.println("Наши напитки: ");
        for (String line : vm.getDrinkTypes()) {
            System.out.println(line);
        }

        Scanner scan = new Scanner(System.in);
        printHelp();
        while (scan.hasNext()) {
            String command = scan.next();
            switch (command) {
                case "add": {
                    int money = scan.nextInt();
                    processAddMoney(money);
                    break;
                }
                case "get": {
                    int key = scan.nextInt();
                    processGetDrink(key);
                    break;
                }
                case "end": {
                    processEnd();
                    return;
                }
                default:
                    System.out.println("Команда не определена");
            }
            scan.nextLine();
        }
    }

    /**
     * обработка добавления денег в автомат
     *
     * @param money - сумма
     */
    private static void processAddMoney(int money) {
        try {
            System.out.println("Текущий баланс: " + vm.addMoney(money));
        } catch (CrumpledMoney crumpledMoney) {
            System.err.println(crumpledMoney.getMessage());
            LOGGER.log(Level.WARNING, "Произошло замятие", crumpledMoney.fillInStackTrace());
        }
    }

    /**
     * обработка получения напитка
     *
     * @param key - код напитка в автомате
     */
    private static void processGetDrink(int key) {
        DrinkType drinkType = null;
        try {
            drinkType = vm.giveMeADrink(key);
        } catch (NoSuchButtonException | NoMoneyException | QuantityException e) {
            System.err.println(e.getMessage());
        }
        if (drinkType != null) {
            System.out.println("Ммм! " + drinkType.getName() + "!");
        } else {
            System.out.println("Напиток почему-то не получен...");
        }
    }

    /**
     * обработка получения сдачи
     */
    private static void processEnd() {
        System.out.println("Ваша сдача: " + vm.getChange());
    }

    /**
     * выводит подсказку по доступным командам
     */
    private static void printHelp() {
        System.out.println("Введите 'add <количество>' для добавления купюр");
        System.out.println("Введите 'get <код напитка>' для получения напитка");
        System.out.println("Введите 'end' для получения сдачи");
    }
}

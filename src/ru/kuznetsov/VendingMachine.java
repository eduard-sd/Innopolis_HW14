package ru.kuznetsov;

import ru.kuznetsov.drinks.ColdDrinkType;
import ru.kuznetsov.drinks.DrinkType;
import ru.kuznetsov.drinks.HotDrinkType;
import ru.kuznetsov.drinks.Product;
import ru.kuznetsov.exceptions.CrumpledMoney;
import ru.kuznetsov.exceptions.NoMoneyException;
import ru.kuznetsov.exceptions.NoSuchButtonException;
import ru.kuznetsov.exceptions.QuantityException;

import java.util.logging.*;

/**
 * Торговый автомат
 */
public class VendingMachine {
    private static final Logger loggerVM = Logger.getLogger(VendingMachine.class.getName());

    private double money = 0;
    private Product[] drinks = new Product[]{
            new Product(ColdDrinkType.COCA, 10),
            new Product(ColdDrinkType.PEPSI, 10),
            new Product(ColdDrinkType.SPRITE, 1),
            new Product(ColdDrinkType.FANTA, 3),
            new Product(HotDrinkType.GREEN_TEA, 100),
            new Product(HotDrinkType.BLACK_TEA, 100),
            new Product(HotDrinkType.AMERICANO, 100),
            new Product(HotDrinkType.CAPPUCINO, 100)
    };

    /**
     * Добавление купюр в автомат
     *
     * @param money - сумма внесенных купюр
     * @return текущий остаток
     */
    public double addMoney(double money) throws CrumpledMoney {
        double a = Math.random();
        if (a <= 0.2){
            this.money += 0;
            throw new CrumpledMoney("Произошло замятие "+money);

        } else{
            this.money += money;
            return this.money;
        }
        // TODO: имитировать замятие купюры - ok
        // TODO: сумму замятых купюр сохранить в исключении - ok
        // TODO: ошибка должна обрабатываться на уровне пользователя (в классе Main) - ok
    }

    /**
     * Получает из автомата выбранный напиток
     *
     * В методе есть проверка корректности кода напитка, проверка остатков данного продукта
     * и достаточно ли денег чтобы его купить. В случае ошибок выбрасывать соответствующее исключение
     *
     * @param key код продукта
     * @return напиток;
     */
    public DrinkType giveMeADrink(int key)  {
        try {
            if (!isKeyValid(key)) {
                // TODO: возвращать соответствующую ошибку - ok
                // Неправильный код товара - товар не возвращается
            }
        }catch (NoSuchButtonException e){
            System.err.println(e.getMessage());
            loggerVM.log(Level.WARNING,"Неправильный код товара",e.fillInStackTrace());
            return null;
        }
        Product selected = drinks[key];
        try {
            if(!isMoneyEnough(selected));{
                // TODO: возвращать соответствующую ошибку - ok
                // Нехватает денег - товар не возвращается
            }
        } catch (NoMoneyException e) {
            System.err.println(e.getMessage());
            loggerVM.log(Level.WARNING,"Нехватает денег",e.fillInStackTrace());
            return null;
        }

        DrinkType drink = null;
        try {
            drink = selected.take();
        } catch (QuantityException e) {
            System.err.println(e.getMessage());
            loggerVM.log(Level.WARNING,"Такой напиток закончился",e.fillInStackTrace());
            return null;
        }
        money -= drink.getPrice();
        return drink;
    }

    /**
     * Формирует список товаров
     *
     * @return строки с информацией о товаре
     */
    public String[] getDrinkTypes() {
        String[] result = new String[drinks.length];
        for (int i = 0; i < drinks.length; i++) {
            result[i] = String.format("%d - %12s: %6.2f руб", i, drinks[i].getName(),drinks[i].getPrice());
        }
        return result;
    }

    /**
     * Проверка что хватает денег на выбранный напиток
     *
     * @param selected - выбранный напиток
     * @return true если денег хватает, иначе - false
     */
    private boolean isMoneyEnough(Product selected) throws NoMoneyException{
        if (money >= selected.getPrice()){
            return true;
        }else {
            throw new NoMoneyException("Нехватает денег");
        }
    }

    /**
     * Проверка что выбрали существующий тип напитка
     * Не проверяет остаток, только границы массива
     *
     * @param key номер напитка
     * @return true если есть напиток с таким номером, иначе false
     */
    private boolean isKeyValid(int key) throws NoSuchButtonException {
        if ((key >= 0 && key < drinks.length)) {
            return true;
        }else {
            throw new NoSuchButtonException("Неправильный код товара");
        }
    }

    /**
     * Выдать сдачу.
     * Количество введеных купюр обнуляется
     *
     * @return остаток
     */
    public double getChange() {
        double money = this.money;
        this.money = 0;
        return money;
    }
}

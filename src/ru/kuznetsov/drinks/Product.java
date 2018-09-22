package ru.kuznetsov.drinks;

/**
 * Класс-обертка "Информация по товару"
 * <p>
 * Добавляет к типу товара дополнительное поле: количество
 */
public class Product {
    private final DrinkType drinkType;
    private int quantity;

    public Product(DrinkType drinkType, int quantity) {
        this.drinkType = drinkType;
        this.quantity = quantity;
    }

    /**
     * Изъятие напитка из хранилища
     * Меняет количество товара в хранлище
     *
     * @return тип напитка
     */
    public DrinkType take() {
        quantity--;
        return drinkType;
    }
    public int getQuantityInfo() {
        return quantity;
    }


    public String getName() {
        return drinkType.getName();
    }

    public double getPrice() {
        return drinkType.getPrice();
    }
}

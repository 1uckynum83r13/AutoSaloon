public class Car {
    private int carId;    // Первичный ключ автомобиля;
    private String carMarkName;    // Марка автомобиля;
    private String carModelName;    // Модель автомобиля;
    private String equipment;    // Комплектация;
    private String year;    // Год выпуска;
    private int price;    // Цена;

    public Car (int carId, String carMarkName, String carModelName, String equipment, String year, int price) {
        this.carId = carId;
        this.carMarkName = carMarkName;
        this.carModelName = carModelName;
        this.equipment = equipment;
        this.year = year;
        this.price = price;
    }

    public int getCarId() {
        return carId;
    }

    public String getCarMarkName() {
        return carMarkName;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public String getEquipment() {
        return equipment;
    }

    public String getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }
}
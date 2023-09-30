public class Cars {
    private int carId;    // Первичный ключ автомобиля;
    private String carMarkName;    // Марка автомобиля;
    private String carModelName;    // Модель автомобиля;
    private String equipment;    // Комплектация;
    private String characteristics;    // Характеритики;
    private int price;    // Цена;

    public Cars(int carId, String carMarkName, String carModelName, String equipment, String characteristics, int price) {
        this.carId = carId;
        this.carMarkName = carMarkName;
        this.carModelName = carModelName;
        this.equipment = equipment;
        this.characteristics = characteristics;
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

    public String getCharacteristics() {
        return characteristics;
    }

    public int getPrice() {
        return price;
    }
}

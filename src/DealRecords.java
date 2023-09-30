public class DealRecords {
    private int dealId;    // Первичный ключ сделки;
    private int personId;    // Первичный ключ клиента;
    private int carId;    // Первичный ключ автомобиля;
    private int dealTime;    // Время сделки;
    private int managerId;    // Первичный ключ менеджера;

    public DealRecords(int dealId, int personId, int carId, int dealTime, int managerId) {
        this.dealId = dealId;
        this.personId = personId;
        this.carId = carId;
        this.dealTime = dealTime;
        this.managerId = managerId;
    }

    public int getDealId() {
        return dealId;
    }

    public int getPersonId() {
        return personId;
    }

    public int getCarId() {
        return carId;
    }

    public int getDealTime() {
        return dealTime;
    }

    public int getManagerId() {
        return managerId;
    }
}

public class TestDriveCarRecords {
    private int testDriveRecordId;    // Первичный ключ записи о тест-драйве;
    private int personId;    // Первичный ключ клиента;
    private int carId;    // Первичный ключ автомобиля;
    private int dateOfRent;    // Время начала тест-драйва;
    private int dateOfReturn;    // Время возврата с тест-драйва;
    private int managerId;    // Первичный ключ менеджера;

    public TestDriveCarRecords(int testDriveRecordId, int personId, int carId, int dateOfRent, int dateOfReturn, int managerId) {
        this.testDriveRecordId = testDriveRecordId;
        this.personId = personId;
        this.carId = carId;
        this.dateOfRent = dateOfRent;
        this.dateOfReturn = dateOfReturn;
        this.managerId = managerId;
    }

    public int getTestDriveRecordId() {
        return testDriveRecordId;
    }

    public int getPersonId() {
        return personId;
    }

    public int getCarId() {
        return carId;
    }

    public int getDateOfRent() {
        return dateOfRent;
    }

    public int getDateOfReturn() {
        return dateOfReturn;
    }

    public int getManagerId() {
        return managerId;
    }
}

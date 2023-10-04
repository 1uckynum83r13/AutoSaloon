public class TestDriveCar extends Car {
    private int transferDate;
    public TestDriveCar(int carId, String carMarkName, String carModelName, String equipment, String year, int price, int transferDate) {
        super(carId, carMarkName, carModelName, equipment, year, price);
        this.transferDate = transferDate;
    }

    public int getTransferDate() {
        return transferDate;
    }
}

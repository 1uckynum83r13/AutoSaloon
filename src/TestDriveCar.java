public class TestDriveCar extends Cars {
    private int transferDate;
    public TestDriveCar(int carId, String carMarkName, String carModelName, String equipment, String year, String characteristics, int price, int transferDate) {
        super(carId, carMarkName, carModelName, equipment, year, characteristics, price);
        this.transferDate = transferDate;
    }

    public int getTransferDate() {
        return transferDate;
    }
}

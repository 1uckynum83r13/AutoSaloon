public class Manager {
    private int managerId;    // Первичный ключ менеджера;
    private String managerName;    // Имя менеджера;
    private String managerLastName;    // Фамилия менеджера;
    private String managerTelNumber;    // Контактный номер менеджера;

    public Manager(int managerId, String managerName, String managerLastName, String managerTelNumber) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.managerLastName = managerLastName;
        this.managerTelNumber = managerTelNumber;
    }

    public int getManagerId() {
        return managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    public String getManagerTelNumber() {
        return managerTelNumber;
    }
}

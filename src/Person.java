public class Person {
    private int personId;    // Первичный ключ клиента;
    private String personName;    // Имя клиента;
    private String personTelNumber;    // Контактный номер клиента;

    public Person(int personId, String personName, String personTelNumber) {
        this.personId = personId;
        this.personName = personName;
        this.personTelNumber = personTelNumber;
    }

    public int getPersonId() {
        return personId;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonTelNumber() {
        return personTelNumber;
    }
}

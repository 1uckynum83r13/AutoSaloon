import java.sql.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
public class DB {
    private static Connection connection;
    private static final String database = "AutoSaloon.db";    // База Данных;
    private static final String carCatalog = "carCatalog";    // Список автомобилей;
    private static final String personTable = "personTable";    // Список клиентов;
    private static final String testDriveCarCatalog = "testDriveCarCatalog";    // Список автомобилей для тест-драйва;
    private static final String testDriveRecordsTable = "testDriveRecordsTable";    // Записи о тест-драйве;
    private static final String dealRecordsTable = "dealRecordsTable";    // Проданные автомобили;
    private static final String managerTable = "managerTable";    // Список менеджеров;



    static void openConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + database);
    }

    static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }



    // 1. Создать таблицу для автомобилей; v
    static void CreateTableForCarCatalog() throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = """
                CREATE TABLE IF NOT EXISTS carCatalog (
                    carId INTEGER PRIMARY KEY AUTOINCREMENT,
                    carMarkName STRING,
                    carModelName STRING,
                    equipment STRING,
                    year STRING,
                    price INTEGER
                 )""";
        statement.executeUpdate(SQL);
    }

    // 1.1 Добавить новый автомобиль; v
    static void addCar(String carMarkName, String carModelName, String equipment, String year, int price) throws SQLException {
        String SQL = "INSERT INTO " + carCatalog + " (carMarkName, carModelName, equipment, year, price) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, carMarkName);
        preparedStatement.setString(2, carModelName);
        preparedStatement.setString(3, equipment);
        preparedStatement.setString(4, year);
        preparedStatement.setInt(5, price);

        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            System.out.format("Добавлен автомобиль с ID: %d",
                    generatedKeys.getLong(1));
        }
    }

    // 1.2 Получить список всех автомобилей; v
    public static ArrayList<String> getCarCatalog() throws SQLException {
        ArrayList<String> CarCatalog = new ArrayList<>();
        String SQL = "SELECT carMarkName, carModelName, equipment, year FROM carCatalog";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {
            while (resultSet.next()) {
                String carMark = resultSet.getString("carMarkName");
                String carModel = resultSet.getString("carModelName");
                String equipment = resultSet.getString("equipment");
                String year = resultSet.getString("year");

                CarCatalog.add(carMark);
                CarCatalog.add(carModel);
                CarCatalog.add(equipment);
                CarCatalog.add(year);
            }
        }
        return CarCatalog;
    }

    // 1.3 Получить автомобиль по его первичному ключу; v
    public static ArrayList<String> getCarByCarId(int carId) throws SQLException {
        ArrayList<String> foundCar = new ArrayList<>();
        String SQL = "SELECT carMarkName, carModelName, equipment, year FROM carCatalog WHERE carId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, carId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String carMark = resultSet.getString("carMarkName");
                    String carModel = resultSet.getString("carModelName");
                    String equipment = resultSet.getString("equipment");
                    String year = resultSet.getString("year");

                    foundCar.add(carMark);
                    foundCar.add(carModel);
                    foundCar.add(equipment);
                    foundCar.add(year);
                }
            }
            return foundCar;
        }
    }



    // 2. Создать таблицу для клиентов; v
    static void CreateTableForPerson () throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = """
                CREATE TABLE IF NOT EXISTS personTable (
                    personId INTEGER PRIMARY KEY AUTOINCREMENT,
                    personName STRING,
                    personTelNumber STRING
                 )""";
        statement.executeUpdate(SQL);
    }

    // 2.1 Создать клиента; v
    static void addPerson (String personName, String personTelNumber) throws SQLException {
        String SQL = "INSERT INTO " + personTable + " (personName, personTelNumber) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, personName);
        preparedStatement.setString(2, personTelNumber);

        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            System.out.format("Добавлен клиент с ID: %d ",
                    generatedKeys.getLong(1));
        }
    }

    // 2.2 По контактному номеру клиента - получить первичный ключ клиента;    -- ???!!!
    public static int getPersonIdByPersonTelNum (String personTelNumber) throws SQLException {
        String SQL = "SELECT personId FROM personTable WHERE personTelNumber = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, personTelNumber);
            int personId = 0;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    personId = resultSet.getInt("personId");
                }
            }
            return personId;
        }
    }

    // 2.3 По первичному ключу клиента - получить имя клиента; v
    public static String getPersonNameByPersonId(int Id) throws SQLException {
        String SQL = "SELECT personName FROM personTable WHERE personId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, Id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("personName");
                }
            }
        }
        return null;
    }



    // 3. Создать таблицу автомобилей для тест-драйва; v
    static void CreateTableForTestDriveCarCatalog() throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = """
                CREATE TABLE IF NOT EXISTS testDriveCarCatalog (
                    testDriveCarID INTEGER PRIMARY KEY AUTOINCREMENT,
                    carMarkName STRING,
                    carModelName STRING,
                    equipment STRING,
                    year STRING,
                    price INTEGER,
                    transferDate DATETIME
                 )""";
        statement.executeUpdate(SQL);
    }

    // 3.1 Добавить автомобиль для тест-драйва; v
    static void addTestDriveCar(String carMarkName, String carModelName, String equipment, String year, int price) throws SQLException {
        String SQL = "INSERT INTO " + testDriveCarCatalog + " (carMarkName, carModelName, equipment, year, price, transferDate) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, carMarkName);
        preparedStatement.setString(2, carModelName);
        preparedStatement.setString(3, equipment);
        preparedStatement.setString(4, year);
        preparedStatement.setInt(5, price);
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        preparedStatement.setTimestamp(6, currentTimestamp);


        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            System.out.format("Добавлен автомобиль для тест-драйва с ID: %d",
                    generatedKeys.getLong(1));
        }
    }

    // 3.2 Получить доступные автомобили для тест-драйва;
    public static ArrayList<String> getAvailableTestDriveCar () throws SQLException {
        ArrayList<String> availableCars = new ArrayList<>();
        String SQL = "SELECT carMarkName, carModelName, equipment, year FROM carCatalog WHERE  IS NULL";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {
            while (resultSet.next()) {
                String availableCar = resultSet.getString("availableCar");
                availableCars.add(availableCar);
            }
        }
        return availableCars;
    }



    // 4. Создать таблицу о поездках в тест-драйве; v
    static void CreateTableForTestDriveRecords() throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = """
                CREATE TABLE IF NOT EXISTS testDriveRecordsTable (
                    ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    personId INTEGER,
                    carId INTEGER,
                    managerId INTEGER,
                    dateOfRent DATETIME,
                    dateOfReturn DATETIME
                 )""";
        statement.executeUpdate(SQL);
    }

    // Добавить событие о тест-драйве; v
    static void makeTestDriveRecord (int personId, int carId, int managerId) throws SQLException {
        String SQL = "INSERT INTO " + testDriveRecordsTable + " (personId, carId, managerId, dateOfRent, dateOfReturn) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setInt(1, personId);
        preparedStatement.setInt(2, carId);
        preparedStatement.setInt(3, managerId);
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        preparedStatement.setTimestamp(4, currentTimestamp);
        preparedStatement.setNull(5, Types.TIMESTAMP);


        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            System.out.format("Добавлена запись о тест-драйве с ID: %d",
                    generatedKeys.getLong(1));
        }
    }



    // 5. Создать таблицу для купленных автомобилей; v
    static void CreateTableForDealRecords() throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = """
                CREATE TABLE IF NOT EXISTS dealRecordsTable (
                    ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    personId INTEGER,
                    carId INTEGER,
                    managerId INTEGER,
                    dealTime DATETIME
                 )""";
        statement.executeUpdate(SQL);
    }

    // 5.1 Добавить сделку; v
    static void addDeal (int personId, int carId, int managerId) throws SQLException {
        String SQL = "INSERT INTO " + dealRecordsTable + " (personId, carId, managerId, dealTime) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, personId);
        preparedStatement.setInt(2, carId);
        preparedStatement.setInt(3, managerId);
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        preparedStatement.setTimestamp(4, currentTimestamp);


        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            System.out.format("Добавлен сделка с ID: %d",
                    generatedKeys.getLong(1));
        }
    }



    // 6. Создать таблицу для менеджеров; v
    static void CreateTableForManager() throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = """
                CREATE TABLE IF NOT EXISTS managerTable (
                    managerId INTEGER PRIMARY KEY AUTOINCREMENT,
                    managerName STRING,
                    managerLastName STRING,
                    managerTelNumber STRING
                 )""";
        statement.executeUpdate(SQL);
    }

    // 6.1 Добавить менеджера; v
    static void addManager (String managerName, String managerLastName, String managerTelNumber) throws SQLException {
        String SQL = "INSERT INTO " + managerTable + " (managerName, managerLastName, managerTelNumber) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, managerName);
        preparedStatement.setString(2, managerLastName);
        preparedStatement.setString(3, managerTelNumber);

        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            System.out.format("Добавлен менеджер с ID: %d",
                    generatedKeys.getLong(1));
        }
    }
}


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MainRepository {

    private static final String DB_USERNAME = "postgres";

    private static final String DB_PASSWORD = "9783507";

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        UserRepository userRepository = new UserRepositoryJdbcImpl(connection);

        List<User> users = userRepository.findAll();
//
//        users.forEach(user -> System.out.println(user.getName()));

        // hw
        // 1 task
        userRepository.insertSixUsers();
        users.forEach(user -> System.out.println(user.getName()));

        // 2 task
        System.out.println("task2");
        List<User> filteredUsers = userRepository.getAdults();
        filteredUsers.forEach(user -> System.out.println(user.getAge()));
    }
}
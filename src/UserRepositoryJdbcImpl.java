
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private Connection connection;


    private static final String SQL_SELECT_FROM_DRIVERS = "select id, first_name, last_name, age from drivers";
    private static final String SQL_INSERT_INTO_DRIVERS = "insert into drivers (first_name, last_name, age) values ('Ivan', 'Ivanov', 19)," +
            "('Bogdan', 'Stepanov', 14)," +
            "('Eugene', 'Ivanov', 23)," +
            "('Anastasia', 'Morozova', 17)," +
            "('Kristina', 'Kropotova', 27)," +
            "('Ildar', 'Khabibullin', 19)";

    public UserRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertSixUsers() throws SQLException {
        Statement statement = connection.createStatement();
        int affectedRows = statement.executeUpdate(SQL_INSERT_INTO_DRIVERS);
    }

    @Override
    public List<User> getAdults() throws SQLException {
        List<User> result = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_FROM_DRIVERS);

        while (resultSet.next()) {
            User user = new User(
                    resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age")
            );
            if (user.getAge() >= 18) {
                result.add(user);
            }
        }
        return result;
    }

    @Override
    public List<User> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_FROM_DRIVERS);

        List<User> result = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age")
            );
            result.add(user);
        }
        return result;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void remove(User entity) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public List<User> findAllByAge(Integer age) throws SQLException {
        return null;
    }
}
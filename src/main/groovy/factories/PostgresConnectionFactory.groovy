package factories

import java.sql.*

class PostgresConnectionFactory implements IConnectionFactory{
    private static PostgresConnectionFactory instance;
    private Connection connection;

    private PostgresConnectionFactory() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/LinkeTinder",
                    "postgres","jonas1234" )

            if (connection == null) {
                println("Falha na tentativa de conexão com o banco de dados!")
            }
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    static synchronized PostgresConnectionFactory getInstance() {
        if (instance == null) {
            instance = new PostgresConnectionFactory();
        }
        return instance;
    }

    @Override
    Connection getConnection() {
        return connection
    }
}

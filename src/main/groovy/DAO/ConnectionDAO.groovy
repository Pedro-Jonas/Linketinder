package DAO

import java.sql.*

class ConnectionDAO {
    static Connection connection() {
        Connection connection
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/LinkeTinder",
                    "postgres","jonas1234" )

            if (connection == null) {
                println("Falha na tentativa de conexão com o banco de dados!")
            }
        } catch (SQLException e) {
            throw new RuntimeException(e)
        }
        return connection
    }
}

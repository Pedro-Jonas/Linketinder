package factories

import java.sql.Connection

interface IConnectionFactory {
    Connection getConnection()
}
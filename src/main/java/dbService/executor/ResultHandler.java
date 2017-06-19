package dbService.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nb on 18.06.17.
 */
public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}

package Person.advanced;

import java.sql.ResultSet;

public interface RowMapper<T> {//定义的一个泛型接口
    /**
     * 约束封装对象的ORM
     */

    public T getRow(ResultSet resultSet);
}

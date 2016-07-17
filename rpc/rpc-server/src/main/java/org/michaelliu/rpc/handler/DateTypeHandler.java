package org.michaelliu.rpc.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

/**
 * Created by Michael on 7/12/16.
 */
public class DateTypeHandler implements TypeHandler {

    private static final Log log = LogFactory.getLog(DateTypeHandler.class);

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i,
                             Object o, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public Object getResult(ResultSet resultSet, String s) throws SQLException {
        return timestampFormat(resultSet.getTimestamp(s));
    }

    @Override
    public Object getResult(ResultSet resultSet, int i) throws SQLException {
        return timestampFormat(resultSet.getTimestamp(i));
    }

    @Override
    public Object getResult(CallableStatement callableStatement, int i) throws SQLException {
        return timestampFormat(callableStatement.getTimestamp(i));
    }

    private Object timestampFormat(Timestamp timestamp) {
        log.info("timestamp is: " + timestamp);
        String dateString = "";
        if (timestamp != null) {
            dateString = timestamp.toString().substring(0, 19);
        }
        log.info("date converts to string: " + dateString);
        return dateString;
    }

}

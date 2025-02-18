package examemedico.exception;

public class DatabaseException extends RuntimeException {

    private int error;
    private String sql;

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseException(String message, Throwable cause, int errorCode, String sqlState) {
        super(message, cause);
        this.error = errorCode;
        this.sql = sqlState;
    }

    public int getError() {
        return error;
    }

    public String getSql() {
        return sql;
    }

    @Override
    public String toString() {
        return "DatabaseException{" +
                "message='" + getMessage() + '\'' +
                ", errorCode=" + error +
                ", sqlState='" + sql + '\'' +
                '}';
    }
}

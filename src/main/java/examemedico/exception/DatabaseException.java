package examemedico.exception;

public class DatabaseException extends RuntimeException {

    private Integer error;
    private String sql;

    public DatabaseException(String message, Throwable cause, Integer errorCode, String sqlState) {
        super(message, cause);
        this.error = errorCode;
        this.sql = sqlState;
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

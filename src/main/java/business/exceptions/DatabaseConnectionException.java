package business.exceptions;

public class DatabaseConnectionException extends Exception
{
    public DatabaseConnectionException(String msg)
    {
        super(msg);
    }
}

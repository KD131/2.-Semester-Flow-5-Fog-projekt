package business.services;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formatter
{
    private static Locale l = Locale.ENGLISH;
    
    public static String formatTimestamp(Timestamp timestamp)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dtf.format(timestamp.toLocalDateTime());
    }
    
    public static String formatPrice(double price)
    {
        // you can add " ,-" to either of them if you'd like.
        // but be aware that it could wrap on the page if you don't do some css or bootstrap to prevent it.
        // and in any case, it takes up more space.
        
        // if price is an int, return String of that (no decimals)
        if (price % 1 == 0)
        {
            return String.valueOf((int) price);
        }
        // formatted to 2 decimal places.
        return String.format(l, "%.2f", price);
    }
}

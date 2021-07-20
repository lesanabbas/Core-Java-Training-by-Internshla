
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock {

    public static void dateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("Date and Time: " + formattedDate);
    }

    public static void main(String[] args) {
        dateTime();
    }
}
package src;
import java.util.Random;

public class Utils {
    public static String generateId() {
        Random random = new Random();
        return String.format("%09d", random.nextInt(1000000000));
    }
}

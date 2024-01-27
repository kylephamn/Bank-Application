package src;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private static final Map<String, Double> conversionRates = new HashMap<>();

    static {
        // These rates are purely for example purposes and do not reflect real-time rates.
        // The rates are based on USD. For example, 1 USD = 0.85 EUR.
        conversionRates.put("USDEUR", 0.9159);
        conversionRates.put("USDGBP", 0.7827);
        conversionRates.put("USDJPY", 147.3994);
        conversionRates.put("USDKRW", 1300.1094);
        conversionRates.put("USDMXN", 17.0018);
        // Add rates for conversion from other currencies to USD if needed.
        conversionRates.put("EURUSD", 1.17); // Example rate for 1 EUR = 1.17 USD
        // Add other rates as necessary.
    }

    public static double convert(String fromCurrency, String toCurrency, double amount) {
        // Direct conversion if the rate is known
        String directConversionKey = fromCurrency + toCurrency;
        if (conversionRates.containsKey(directConversionKey)) {
            return amount * conversionRates.get(directConversionKey);
        }
        
        // Two-step conversion through USD if direct conversion is not available
        String toUSDConversionKey = fromCurrency + "USD";
        String fromUSDConversionKey = "USD" + toCurrency;
        if (conversionRates.containsKey(toUSDConversionKey) && conversionRates.containsKey(fromUSDConversionKey)) {
            double amountInUSD = amount * conversionRates.get(toUSDConversionKey);
            return amountInUSD * conversionRates.get(fromUSDConversionKey);
        } else {
            System.out.println("Conversion rate not defined for " + directConversionKey);
            return 0;
        }
    }
}

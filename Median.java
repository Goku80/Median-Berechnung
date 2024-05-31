import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Median
{
    // Methode schnelle Berechnung des k-kleinsten Elements (bzw. Medians) in einer Liste von Double-Werten
    public static double fastMedian(List<Double> values, int k)
    {
        // Basisfall: Wenn die Liste nur ein Element hat, ist dieses Element der Median
        if (values.size() == 1)
        {
            return values.get(0);
        }
        else
        {
            // Wählen eines zufälligen Elements als Pivot
            int randomIndex = (int) (Math.random() * values.size());
            double x = values.get(randomIndex);

            // Listen zum Speichern der Werte, die kleiner, gleich oder größer als das Pivot-Element sind
            List<Double> L = new ArrayList<>();  // Liste für Werte kleiner als x
            List<Double> E = new ArrayList<>();  // Liste für Werte gleich x
            List<Double> G = new ArrayList<>();  // Liste für Werte größer als x

            // Verteilung der Werte in die Listen L, E und G
            for (double xi : values)
            {
                if (xi < x)
                {
                    L.add(xi);
                }
                else if (xi > x)
                {
                    G.add(xi);
                }
                else
                {
                    E.add(xi);
                }
            }

            // Rekursive Aufrufe basierend auf der Position von k relativ zu L, E und G
            if (k <= L.size())
            {
                // k-kleinster Wert befindet sich in L
                return fastMedian(L, k);
            }
            else if (k <= L.size() + E.size())
            {
                // Der Median ist x, da k in der Liste E liegt
                return x;
            }
            else
            {
                // k-kleinster Wert befindet sich in G
                return fastMedian(G, k - L.size() - E.size());
            }
        }
    }

    // Überladene Methode zur Berechnung des Medians eines Arrays von Double-Werten
    public static double fastMedian(Double[] values)
    {
        // Der Median ist das (n+1)/2-kleinste Element
        int k = (values.length + 1) / 2;
        // Umwandlung des Arrays in eine Liste und Aufruf der rekursiven Methode
        return fastMedian(Arrays.asList(values), k);
    }

    public static void main(String[] args)
    {
        // Initialisieren eines Arrays mit zufälligen Double-Werten
        final int size = 1001;
        Double[] values = new Double[size];
        for (int i = 0; i < size; i++)
        {
            values[i] = Math.random();
        }

        // Berechnen des Medians mit der schnellen Methode
        double fastMedianResult = fastMedian(values);

        // Berechnen des Medians mit einer einfachen, aber langsamen Methode (Sortieren und Mittleres Element nehmen)
        Arrays.sort(values);
        double naiveMedianResult = values[values.length / 2];

        // Ausgabe der Ergebnisse beider Methoden
        System.out.println("Fast Median: " + fastMedianResult);
        System.out.println("Naive Median: " + naiveMedianResult);

        // Überprüfen, ob beide Methoden den gleichen Median liefern
        if (fastMedianResult == naiveMedianResult)
        {
            System.out.println("Die Ergebnisse stimmen überein.");
        }
        else
        {
            System.out.println("Die Ergebnisse stimmen nicht überein.");
        }
    }
}


import CopyClasses.Trader;
import CopyClasses.Transaction;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).

        transactions.stream()
                .filter(y -> y.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);

        System.out.println();

        //2. Вывести список неповторяющихся городов, в которых работают трейдеры.

        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        System.out.println();

        //3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.

        transactions.stream()
                .map(t -> t.getTrader())
                .filter(t -> t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .map(t -> t.getName())
                .distinct()
                .sorted()
                .forEach(System.out::println);

        System.out.println();

        //4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.

        transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .forEach(System.out::println);

        System.out.println();

        //5. Выяснить, существует ли хоть один трейдер из Милана.

        boolean result = transactions.stream()
                .map(t -> t.getTrader())
                .distinct()
                .anyMatch(t -> t.getCity().equals("Milan"));

        System.out.println(result);
        System.out.println();

        //6. Вывести суммы всех транзакций трейдеров из Кембриджа.

        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(t -> t.getValue())
                .forEach(System.out::println);

        System.out.println();

        //7. Какова максимальная сумма среди всех транзакций?

        System.out.println(transactions.stream()
                .max(Comparator.comparingInt(Transaction::getValue))
                .map(t -> t.getValue())
                .get());

        System.out.println();

        //8. Найти транзакцию с минимальной суммой.

        System.out.println(transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue))
                .get());

    }
}
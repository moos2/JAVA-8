package lambdasinaction._02stream.basic2;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice{
    public static void main(String ...args){    
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
		
		List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300), 
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),	
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );	
        
		//forEach와 forEachOrdered의 차이 
        System.out.println("Using forEach()");
        String str = "Yashwant Chavan";
        str.chars().forEach(n -> System.out.print((char) n));
 
        System.out.println("\n\nUsing parallel() + forEach()");
        str.chars().parallel().forEach(n -> System.out.print((char) n));
 
        System.out.println("\n\nUsing parallel() + forEachOrdered()");
        str.chars().parallel().forEachOrdered(n -> System.out.print((char) n));
        
        
        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
		transactions.stream()
					.filter(tx -> tx.getYear() > 2011)
					.sorted(Comparator.comparing(Transaction::getValue))
					.forEach(x -> System.out.println(x));
        
        // Query 2: What are all the unique cities where the traders work?
		transactions.stream()
					.map(x -> x.getTrader().getCity())
					.distinct()
					.forEach(System.out::println);
					;

        // Query 3: Find all traders from Cambridge and sort them by name.
        transactions.stream()					//Stream<Transaction>
        			.map(tx -> tx.getTrader())  //Stream<Trader>
        			.filter(tr -> tr.getCity().equals("Cambridge"))
        			.distinct()
        			.sorted(Comparator.comparing(Trader::getName))
        			.forEach(System.out::println)
        			;
        
        // Query 4: Return a string of all traders names sorted alphabetically.
        // map(), sorted(), reduce()
        String names =
	        transactions.stream()
	        			.map(tx -> tx.getTrader().getName())  //Stream<String>
	        			.sorted()
	        			.distinct()
	        			.reduce("names: ", (s1, s2) -> s1 +" / "+ s2);
        System.out.println(names);
        
        // Query 5: Are there any trader based in Milan?
        boolean milanBased =
        		transactions.stream()
        					.anyMatch(x -> x.getTrader().getCity().equals("Milan"))
        					;
        System.out.println(milanBased);
        
        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        System.out.println("Before Update transactions: "+transactions);
        transactions.stream()
        			.map(tx -> tx.getTrader())  //Stream<Trader>
        			.filter(tr -> tr.getCity().equals("Milan"))
        			.forEach(tr -> tr.setCity("Cambridge"));
        System.out.println("After Update transactions: "+transactions);
        
        // Query 7: What's the highest value in all the transactions?
        //max()까지만 하면 Optional<Int> 타입이다.
        //getAsInt()로 해주면 인트로 받을 수 있다.
        int maxValue = 
        		transactions.stream()
        			.mapToInt(Transaction::getValue)
        			.max()  //OptionalInt
        			.getAsInt()  //Int
        			;


    }
}
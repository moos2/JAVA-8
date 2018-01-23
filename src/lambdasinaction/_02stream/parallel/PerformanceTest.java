package lambdasinaction._02stream.parallel;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by vega on 2017-06-23.
 */

public class PerformanceTest {
    public static void main(String[] args) {
        System.out.println(Math.pow(10,9));
        System.out.println( 1000000 / Math.pow(10,9));


        //Calculating square root of even numbers from 1 to N
        int min = 1;
        int max = 1000000;

        List<Integer> sourceList = new ArrayList<>();
        for (int i = min; i < max; i++) {
            sourceList.add(i);
        }
        /*
        Collections: Elapsed time:	 27849487 ns 	(0.027849 seconds)
        Streams: Elapsed time:		 1340670549 ns 	(1.340671 seconds)
        Parallel streams: Elapsed time:	 76574436 ns 	(0.076574 seconds)
        */
       // List<Double> result = new LinkedList<>();

        /*
        Collections: Elapsed time:	 24612846 ns 	(0.024613 seconds)
        Streams: Elapsed time:		 1166304546 ns 	(1.166305 seconds)
        Parallel streams: Elapsed time:	 205236184 ns 	(0.205236 seconds)
         */
        List<Double> result = new ArrayList<>();


        // result 인스턴스 변수를 LinkedList와 ArrayList로 생성하였다.
        // 각각 주석 처리한 다음 실행해보면 어떤 것이 더 빠른지 확인할 수 있다.
        // 바로 실행하면 ArrayList의 인스턴스 변수 result로 실행된다.
        // line 35 LinkedArray     ,    line 42 ArrayList
        
        //Collections approach
        long t0 = System.nanoTime();
        long elapsed = 0;

        for (Integer i : sourceList) {
            if(i % 2 == 0){
                //제곱근 계산
                result.add(Math.sqrt(i));
            }
        }

        elapsed = System.nanoTime() - t0;
        // %d : 정수, %f : 실수
        System.out.printf("Collections: Elapsed time:\t %d ns \t(%f seconds)%n", elapsed, elapsed / Math.pow(10, 9));


        //Stream approach
        Stream<Integer> stream = sourceList.stream();
        t0 = System.nanoTime();
        result = stream.filter(i -> i%2 == 0).map(i -> Math.sqrt(i)).collect(Collectors.toList());
        elapsed = System.nanoTime() - t0;
        System.out.printf("Streams: Elapsed time:\t\t %d ns \t(%f seconds)%n", elapsed, elapsed / Math.pow(10, 9));


        //Parallel stream approach
        stream = sourceList.stream().parallel();
        t0 = System.nanoTime();
        result = stream.filter(i -> i%2 == 0).map(i -> Math.sqrt(i)).collect(Collectors.toList());
        elapsed = System.nanoTime() - t0;
        System.out.printf("Parallel streams: Elapsed time:\t %d ns \t(%f seconds)%n", elapsed, elapsed / Math.pow(10, 9));
    }
}

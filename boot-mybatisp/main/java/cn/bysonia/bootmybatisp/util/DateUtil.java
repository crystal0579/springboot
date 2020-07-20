package cn.bysonia.bootmybatisp.util;



import java.time.Duration;
import java.time.LocalTime;

public class DateUtil {
    public static void  testDuration(){
        LocalTime start = LocalTime.of(1, 20, 25, 1024);
        LocalTime end = LocalTime.of(3, 22, 27, 1544);

        Duration duration = Duration.between(start, end);
       System.out.println(duration.getSeconds());

        Duration fromChar1 = Duration.parse("P1DT1H10M10.5S");
        System.out.println(fromChar1.getSeconds());
        Duration fromChar2 = Duration.parse("PT10M");
    }

    public static void main(String[] args){
        testDuration();
    }
}

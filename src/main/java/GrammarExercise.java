import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        Scanner sc=new Scanner(System.in);

        String firstWordList = sc.next();
        String secondWordList = sc.next();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        Stream<String> resultStream=result.stream();
        resultStream.sorted().forEach(System.out::println);
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        Pattern pattern=Pattern.compile(",");

        boolean isAllLetter1=pattern.splitAsStream(firstWordList).allMatch(e->e.matches("[a-zA-Z]+"));
        if (!isAllLetter1){
            throw new RuntimeException("input not valid");
        }
        boolean isAllLetter2=pattern.splitAsStream(secondWordList).allMatch(e->e.matches("[a-zA-Z]+"));
        if (!isAllLetter2){
            throw new RuntimeException("input not valid");
        }

        String[] a1=firstWordList.split(",");
        String[] a2=secondWordList.split(",");

        List<String> l1=new ArrayList<>();
        List<String> l2=new ArrayList<>();


        for (String v1:a1) {
            v1=v1.toUpperCase().replace(""," ").trim();
            l1.add(v1);
        }

        for (String v2:a2) {
            v2=v2.toUpperCase().replace(""," ").trim();
            l2.add(v2);
        }

        Stream<String> stringStream1= l1.stream();

        return stringStream1.distinct().sorted().filter(e1->l2.stream().anyMatch(e2->e2.equalsIgnoreCase(e1))).collect(Collectors.toList());
    }
}

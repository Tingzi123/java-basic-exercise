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
        resultStream.sorted().forEach(e->{
            e=e.toUpperCase().replace("", " ").trim();
            System.out.print(e+" ");
        });
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

        Stream<String> stringStream1=pattern.splitAsStream(firstWordList);

        return stringStream1.distinct().sorted().filter(e1->pattern.splitAsStream(secondWordList).anyMatch(e2->e2.equalsIgnoreCase(e1))).collect(Collectors.toList());
    }
}

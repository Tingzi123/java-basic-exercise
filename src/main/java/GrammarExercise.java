import java.util.ArrayList;
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
        resultStream.forEach(System.out::println);
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        Pattern pattern=Pattern.compile(",");
        Stream<String> stringStream1=pattern.splitAsStream(firstWordList);
        Stream<String> stringStream2=pattern.splitAsStream(secondWordList);

        Stream<String> t1=stringStream1;
        Stream<String> t2=stringStream2;
        boolean isAllLetter1=t1.allMatch(e->e.matches("[a-zA-Z]+"));
        if (!isAllLetter1){
            throw new RuntimeException("input not valid");
        }
        boolean isAllLetter2=t2.allMatch(e->e.matches("[a-zA-Z]+"));
        if (!isAllLetter2){
            throw new RuntimeException("input not valid");
        }

        List<String> reList=new ArrayList<>();
        Stream<String> re=reList.stream();

        stringStream1.distinct().sorted();
        stringStream2.distinct().sorted().forEach(e1->{
            Stream.concat(re,stringStream2.filter(e2-> e2.equalsIgnoreCase(e1)));
        });

        return re.collect(Collectors.toList());
    }
}

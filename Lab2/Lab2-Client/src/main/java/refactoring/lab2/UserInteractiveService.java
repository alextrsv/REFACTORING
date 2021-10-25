package refactoring.lab2;


import io.grpc.ManagedChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInteractiveService {


    private static final Scanner in = new Scanner(System.in);
    private static DictionaryClientService dictionaryClientService;


    public static void start(ManagedChannel channel) {
        dictionaryClientService = new DictionaryClientService(channel);
        run();
    }
    public static void run(){
        String targetWord;

        do {
            targetWord = in.nextLine();
            System.out.println(targetWord);
            if (dictionaryClientService.isWordPresent(targetWord)){
                System.out.println(dictionaryClientService.getSeemsWords(targetWord));
            } else {
                System.out.println("Неизвестное слово. Хотите добавить его в словарь (y/n)?");
                if (in.nextLine().equals("y")) saveNewWord(targetWord);
            }
        }while(!targetWord.equals("q"));
    }

    private static void saveNewWord(String word) {
        List<String> prefixes = getPrefixes();
        String root = getRoot();
        List<String> postfixes = getPostfixes();

        if (word.equals(makeWholeWord(prefixes, root, postfixes))){
            System.out.println(dictionaryClientService.saveNewWordToServer(prefixes, root, postfixes));     //////////////////////////////////////////////3 saveNewWOrd
        }else{
            printMessage("введенное целиуом слово не совпадает с веденным по частям");
        }
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }

    private static String makeWholeWord(List<String> prefixes, String root, List<String> postfixes) {
        return (String.format("%s%s%s",
                String.join("", prefixes),
                root,
                String.join("", postfixes)));
    }


    private static List<String> getPrefixes(){
        return getMultiParts("приставка  ");
    }

    private static List<String> getPostfixes(){
       return getMultiParts("суффикс или окончание:  ");
    }

    private static String getRoot(){
        return readWordPart("корень: ");
    }

    private static List<String> getMultiParts(String message){
        List<String> multiParts = new ArrayList<>();
        boolean isInputContinue = true;

        while (isInputContinue) {
            String part = readWordPart(message);
            if (!part.trim().equals(""))
                multiParts.add(part);
            else isInputContinue = false;
        }
        return multiParts;
    }

    private static String readWordPart(String message){
        printMessage(message);
        return in.nextLine();
    }
}

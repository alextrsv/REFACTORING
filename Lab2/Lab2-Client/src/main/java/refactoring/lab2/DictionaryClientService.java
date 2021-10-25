package refactoring.lab2;

import io.grpc.ManagedChannel;
import refactoring.lab2.grpc.DictionaryServiceGrpc;
import refactoring.lab2.grpc.DictionaryServiceOuterClass;

import java.util.List;

public class DictionaryClientService {

    private static DictionaryServiceGrpc.DictionaryServiceBlockingStub dictionaryStub;

    public DictionaryClientService(){ }
    public DictionaryClientService(ManagedChannel channel){
        dictionaryStub = DictionaryServiceGrpc.newBlockingStub(channel);
    }


    public String getSeemsWords(String targetWord) {
        DictionaryServiceOuterClass.GetSingleRootWordsResponse response =
                dictionaryStub.getSingleRootWords(makeSingleRootWordsRequest(targetWord));
        return String.join("\n", response.getSingleRootWordsList());
    }


    private DictionaryServiceOuterClass.GetSingleRootWordsRequest makeSingleRootWordsRequest(String targetWord) {
        return DictionaryServiceOuterClass.GetSingleRootWordsRequest
                .newBuilder()
                .setWordContent(targetWord)
                .build();
    }

    public boolean isWordPresent(String targetWord) {
        DictionaryServiceOuterClass.IsWordExistResponse response =
                dictionaryStub.isWordExist(makeIsWordExistRequest(targetWord));
        return response.getIsPresent();
    }

    private DictionaryServiceOuterClass.IsWordExistRequest makeIsWordExistRequest(String content){
        return DictionaryServiceOuterClass.IsWordExistRequest
                .newBuilder()
                .setWordContent(content)
                .build();
    }


    public String saveNewWordToServer(List<String> prefixes, String root, List<String> postfixes) {
        DictionaryServiceOuterClass.SaveNewWordResponse response =
                dictionaryStub.saveNewWord(makeSaveNewWordRequest(prefixes, root, postfixes));
        return response.getMessage();
    }

    private DictionaryServiceOuterClass.SaveNewWordRequest makeSaveNewWordRequest(List<String> prefixes, String root, List<String> postfixes){
        return DictionaryServiceOuterClass.SaveNewWordRequest
                .newBuilder()
                .addAllPrefixes(prefixes)
                .setRoot(root)
                .addAllPostfixes(postfixes)
                .build();
    }

}

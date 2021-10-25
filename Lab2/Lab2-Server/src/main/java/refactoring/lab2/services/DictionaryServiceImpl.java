package refactoring.lab2.services;

import io.grpc.stub.StreamObserver;
import refactoring.lab2.dao.Impl.CrudDAOImpl;
import refactoring.lab2.entities.DictionaryWord;
import refactoring.lab2.entities.Postfix;
import refactoring.lab2.entities.Prefix;
import refactoring.lab2.entities.Root;
import refactoring.lab2.grpc.DictionaryServiceGrpc;
import refactoring.lab2.grpc.DictionaryServiceOuterClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DictionaryServiceImpl extends DictionaryServiceGrpc.DictionaryServiceImplBase {

    CrudDAOImpl<DictionaryWord> dictionaryWordDAO;
    CrudDAOImpl<Root> rootDAO;
    CrudDAOImpl<Prefix> prefixDAO;
    CrudDAOImpl<Postfix> postfixDAO;

    public DictionaryServiceImpl(){
        dictionaryWordDAO = new CrudDAOImpl<>(DictionaryWord.class);
        rootDAO = new CrudDAOImpl<>(Root.class);
        prefixDAO = new CrudDAOImpl<>(Prefix.class);
        postfixDAO = new CrudDAOImpl<>(Postfix.class);
    }

    @Override
    public void isWordExist(DictionaryServiceOuterClass.IsWordExistRequest request,
                         StreamObserver<DictionaryServiceOuterClass.IsWordExistResponse> responseObserver){

        System.out.println(request);

        Optional<DictionaryWord> word = dictionaryWordDAO.findByContent(request.getWordContent());
        DictionaryServiceOuterClass.IsWordExistResponse response = DictionaryServiceOuterClass.
                IsWordExistResponse.newBuilder()
                .setIsPresent(word.isPresent())
                .setWordContent(word.isPresent() ? word.get().getContent() : "")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getSingleRootWords(DictionaryServiceOuterClass.GetSingleRootWordsRequest request,
                                   StreamObserver<DictionaryServiceOuterClass.GetSingleRootWordsResponse> responseObserver){


        Optional<DictionaryWord> word = dictionaryWordDAO.findByContent(request.getWordContent());
        Root targetRoot = word.get().getRoot();
        Collection<DictionaryWord> seemsWords = targetRoot.getWords();
        ArrayList<String> singleRootWords = new ArrayList<>();

        for (DictionaryWord seemsWord: seemsWords) {
            singleRootWords.add(seemsWord.toString());
        }

        DictionaryServiceOuterClass.GetSingleRootWordsResponse response = DictionaryServiceOuterClass.
                GetSingleRootWordsResponse.newBuilder()
                .addAllSingleRootWords(singleRootWords)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void saveNewWord(DictionaryServiceOuterClass.SaveNewWordRequest request,
                                   StreamObserver<DictionaryServiceOuterClass.SaveNewWordResponse> responseObserver){

        Optional<Root> root;
        List<Prefix> prefixes = new ArrayList<>();
        List<Postfix> postfixes = new ArrayList<>();


        root = rootDAO.findByContent(request.getRoot());
        if (!root.isPresent()) {

            rootDAO.save(new Root(request.getRoot()));
           root = rootDAO.findByContent(request.getRoot());
        }

        for (String prefixContent: request.getPrefixesList()) {
            Optional<Prefix> prefix = prefixDAO.findByContent(prefixContent);
            if(!prefix.isPresent()) {
                prefixDAO.save(new Prefix(prefixContent));
                prefix = prefixDAO.findByContent(prefixContent);
            }
            prefixes.add(prefix.get());
        }
        for (String postfixContent: request.getPostfixesList()) {
            Optional<Postfix> postfix = postfixDAO.findByContent(postfixContent);
            if(!postfix.isPresent()) {
                postfixDAO.save(new Postfix(postfixContent));
                postfix = postfixDAO.findByContent(postfixContent);
            }
            postfixes.add(postfix.get());
        }


        DictionaryWord newWord = new DictionaryWord();
        newWord.setPrefixes(prefixes);
        newWord.setPostfixes(postfixes);
        newWord.setRoot(root.get());
        newWord.makeContent();
        dictionaryWordDAO.save(newWord);


        DictionaryServiceOuterClass.SaveNewWordResponse response = DictionaryServiceOuterClass.
                SaveNewWordResponse.newBuilder()
                .setMessage("successfully added new word: " + newWord.getContent())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}

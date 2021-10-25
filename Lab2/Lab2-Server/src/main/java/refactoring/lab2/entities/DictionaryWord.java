package refactoring.lab2.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;


@Data
@Entity
@Table(name = "word")
public class DictionaryWord {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//, generator="vehicle-gen")
    private int id;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "prefix_to_word",
            joinColumns = @JoinColumn(name = "prefix_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id")
    )
    public Collection<Prefix> prefixes;

    @ManyToOne(optional = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "root_id")
    public Root root;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "postfix_to_word",
            joinColumns = @JoinColumn(name = "postfix_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id")
    )
    public Collection<Postfix> postfixes;


    @Column(name = "content")
    private String content;


    public DictionaryWord(){
    }

    public DictionaryWord(List<Prefix> prefixesList, Root root, List<Postfix> postfixesList) {
        this.prefixes = prefixesList;
        this.root = root;
        this.postfixes = postfixesList;
        makeContent();
    }

    public void makeContent() {
        content = "";

        prefixes.stream().forEach(p -> content += p.getContent());
        content += root.getContent();
        postfixes.stream().forEach(p -> content += p.getContent());

    }


    @Override
    public String toString(){
        return content;
    }

//    @Override
//    public String toString() {
//        return String.format("%s-%s-%s",
//                String.join("-", prefixes),
//                root,
//                String.join("-", postfixes.toString()));
//    }
}

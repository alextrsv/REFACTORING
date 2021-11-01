package refactoring.lab2.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "postfix")
public class Postfix extends WordPart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

//    @Column(name = "content")
//    private String content;

    @ManyToMany(mappedBy = "postfixes")
    private Collection<DictionaryWord> words;

    public Postfix(){}

    public Postfix(String postfixContent){
        this.content = postfixContent;
    }
}

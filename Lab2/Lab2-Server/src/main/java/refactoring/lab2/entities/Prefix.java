package refactoring.lab2.entities;

import javax.persistence.*;
import java.util.Collection;

//@Data
@Entity
@Table(name = "prefix")
public class Prefix extends WordPart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

//    @Column(name = "content")
//    private String content;

    @ManyToMany(mappedBy = "prefixes")
    private Collection<DictionaryWord> words;

    public Prefix(){}

    public Prefix(String prefixContent){
        this.content = prefixContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Collection<DictionaryWord> getWords() {
        return words;
    }

    public void setWords(Collection<DictionaryWord> words) {
        this.words = words;
    }
}

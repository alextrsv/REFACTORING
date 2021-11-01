package refactoring.lab2.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "root")
public class Root  extends WordPart{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

//    @Column(name = "content")
//    private String content;

    @OneToMany(mappedBy = "root", fetch = FetchType.EAGER)
    private Collection<DictionaryWord> words;

    public Root() {}

    public Root(String rootContent) {
        this.content = rootContent;
    }
}

package refactoring.lab2.entities;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class WordPart {

    protected String content;
}

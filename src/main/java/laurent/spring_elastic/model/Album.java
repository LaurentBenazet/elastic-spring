package laurent.spring_elastic.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "albums")
public class Album {
    @Id
    private String id;
    @Field(type = FieldType.Text, name = "title")
    private String title;
    @Field(type = FieldType.Text, name = "artist")
    private String artist;
    @Field(type = FieldType.Text, name = "releaseYear")
    private String releaseYear;
    @Field(type = FieldType.Text, name = "coverURL")
    private String coverURL;
}

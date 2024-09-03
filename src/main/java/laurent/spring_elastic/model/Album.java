package laurent.spring_elastic.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@Document(indexName = "albums")
@Setting(replicas = 0)
public class Album {
    @Id
    private String id;
    @Field(type = FieldType.Text, name = "title")
    private String title;
    @Field(type = FieldType.Text, name = "artist")
    private String artist;
    @Field(type = FieldType.Integer, name = "releaseYear")
    private int releaseYear;
    @Field(type = FieldType.Text, name = "coverURL")
    private String coverURL;
}

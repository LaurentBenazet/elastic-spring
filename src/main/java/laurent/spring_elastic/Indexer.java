package laurent.spring_elastic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import laurent.spring_elastic.model.Album;
import laurent.spring_elastic.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class Indexer {
    private final AlbumService albumService;

    public void indexAllAlbums() {
        try {
            Resource resource = new ClassPathResource("albums_sample.json");
            ObjectMapper objectMapper = new ObjectMapper();
            List<Album> albums = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {
            });

            albumService.saveAll(albums);

        } catch (Throwable t) {
            log.error(t.getMessage(), t);
        }
    }
}

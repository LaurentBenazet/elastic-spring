package laurent.spring_elastic.repository;

import laurent.spring_elastic.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends ElasticsearchRepository<Album, String>, AlbumCustomRepository {
    Page<Album> findByReleaseYear(int release, Pageable pageable);

    Page<Album> findByTitleContains(String title, Pageable pageable);

    Page<Album> findByReleaseYearAndTitleContains(int release, String title, Pageable pageable);
}

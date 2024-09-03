package laurent.spring_elastic.repository;

import laurent.spring_elastic.model.Album;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.stereotype.Repository;

@Repository
interface AlbumCustomRepository {
    SearchPage<Album> findByTitleWithReleaseYearCount(String title, Pageable pageable);

    SearchPage<Album> findAllWithReleaseYearCount(Pageable pageable);
}

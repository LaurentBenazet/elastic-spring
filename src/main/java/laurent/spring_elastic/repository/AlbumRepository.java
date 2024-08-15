package laurent.spring_elastic.repository;

import laurent.spring_elastic.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends PagingAndSortingRepository<Album, String> {
    Page<Album> findByReleaseYear(String release, Pageable pageable);

    Page<Album> findByTitleContains(String title, Pageable pageable);

    Page<Album> findByReleaseYearAndTitleContains(String release, String title, Pageable pageable);

    Album findById(String id);
}

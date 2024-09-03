package laurent.spring_elastic.service;

import laurent.spring_elastic.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AlbumService {
    Page<Album> findByReleaseYear(int release, Pageable pageable);

    Page<Album> findByTitleContains(String title, Pageable pageable);

    Page<Album> findByReleaseYearAndTitleContains(int release, String title, Pageable pageable);

    Optional<Album> findById(String id);

    void saveAll(List<Album> albums);

    Page<Album> findAll(Pageable pageable);

    SearchPage<Album> findByTitleWithReleaseYearCount(String title, Pageable pageable);

    SearchPage<Album> findAllWithReleaseYearCount(Pageable pageable);
}

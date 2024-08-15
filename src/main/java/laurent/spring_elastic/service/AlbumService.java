package laurent.spring_elastic.service;

import laurent.spring_elastic.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AlbumService {
    Page<Album> findByReleaseYear(String release, Pageable pageable);

    Page<Album> findByTitleContains(String title, Pageable pageable);

    Page<Album> findByReleaseYearAndTitleContains(String release, String title, Pageable pageable);

    Album findById(String id);
}

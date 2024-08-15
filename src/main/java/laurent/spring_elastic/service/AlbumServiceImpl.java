package laurent.spring_elastic.service;

import laurent.spring_elastic.model.Album;
import laurent.spring_elastic.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    @Override
    public Page<Album> findByReleaseYear(String release, Pageable pageable) {
        return albumRepository.findByReleaseYear(release, pageable);
    }

    @Override
    public Page<Album> findByTitleContains(String title, Pageable pageable) {
        return albumRepository.findByTitleContains(title, pageable);
    }

    @Override
    public Page<Album> findByReleaseYearAndTitleContains(String release, String title, Pageable pageable) {
        return albumRepository.findByReleaseYearAndTitleContains(release, title, pageable);
    }

    @Override
    public Album findById(String id) {
        return albumRepository.findById(id);
    }
}

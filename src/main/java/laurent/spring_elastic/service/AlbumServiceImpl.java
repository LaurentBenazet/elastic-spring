package laurent.spring_elastic.service;

import laurent.spring_elastic.model.Album;
import laurent.spring_elastic.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Album> findById(String id) {
        return albumRepository.findById(id);
    }

    @Override
    public void saveAll(List<Album> albums) {
        albumRepository.saveAll(albums);
    }

    @Override
    public Page<Album> findAll(Pageable pageable) {
        return albumRepository.findAll(pageable);
    }
}

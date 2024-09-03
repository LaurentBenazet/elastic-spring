package laurent.spring_elastic.controller;

import laurent.spring_elastic.model.Album;
import laurent.spring_elastic.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping("/search/{title}")
    public Page<Album> getAlbumsByTitle(@PathVariable("title") String title, Pageable pageable) {
        return albumService.findByTitleContains(title, pageable);
    }

    @GetMapping("/release/{release}")
    public Page<Album> getAlbumsByReleaseYear(@PathVariable("release") int release, Pageable pageable) {
        return albumService.findByReleaseYear(release, pageable);
    }

    @GetMapping("/search/{title}/release/{release}")
    public Page<Album> getAlbumsByReleaseYearAndTitle(@PathVariable("release") int release, @PathVariable("title") String title, Pageable pageable) {
        return albumService.findByReleaseYearAndTitleContains(release, title, pageable);
    }

    @GetMapping("/{id}")
    public Optional<Album> getAlbumById(@PathVariable("id") String id) {
        return albumService.findById(id);
    }

    @GetMapping("/")
    public Page<Album> getAllAlbums(Pageable pageable) {
        return albumService.findAll(pageable);
    }

    @GetMapping("/searchWithReleaseYearCounts/{title}")
    public SearchPage<Album> findByTitleWithReleaseYearCount(@PathVariable("title") String title, Pageable pageable) {
        return albumService.findByTitleWithReleaseYearCount(title, pageable);
    }

    @GetMapping("/searchWithReleaseYearCounts/all")
    public SearchPage<Album> findAllWithReleaseYearCount(Pageable pageable) {
        return albumService.findAllWithReleaseYearCount(pageable);
    }
}
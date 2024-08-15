package laurent.spring_elastic.controller;

import laurent.spring_elastic.model.Album;
import laurent.spring_elastic.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Page<Album> getAlbumsByReleaseYear(@PathVariable("release") String release, Pageable pageable) {
        return albumService.findByReleaseYear(release, pageable);
    }

    @GetMapping("/search/{title}/release/{release}")
    public Page<Album> getAlbumsByReleaseYearAndTitle(@PathVariable("release") String release, @PathVariable("title") String title, Pageable pageable) {
        return albumService.findByReleaseYearAndTitleContains(release, title, pageable);
    }

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable("id") String id) {
        return albumService.findById(id);
    }
}
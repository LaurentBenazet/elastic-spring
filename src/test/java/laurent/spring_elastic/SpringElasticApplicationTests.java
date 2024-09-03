package laurent.spring_elastic;

import co.elastic.clients.elasticsearch._types.aggregations.LongTermsBucket;
import laurent.spring_elastic.controller.AlbumController;
import laurent.spring_elastic.model.Album;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregations;
import org.springframework.data.elasticsearch.core.SearchPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Import(TestcontainersConfiguration.class)
@SpringBootTest
@RequiredArgsConstructor
class SpringElasticApplicationTests {

    @Autowired
    private AlbumController albumController;

    @Test
    void canSearchByTitle() {
        Iterable<Album> albums = albumController.getAlbumsByTitle("Exile", PageRequest.of(0, 10));
        List<Album> albumList = new ArrayList<>();
        albums.forEach(albumList::add);

        assert (albumList.get(0).getTitle().equals("Exile"));
        assert (albumList.get(1).getTitle().equals("Exile Extended"));
    }

    @Test
    void canSearchByReleaseYear() {
        Iterable<Album> albums = albumController.getAlbumsByReleaseYear(1998, PageRequest.of(0, 10));
        List<Album> albumList = new ArrayList<>();
        albums.forEach(albumList::add);

        assert (albumList.size() == 10);
        for (Album album : albumList) {
            assert (album.getReleaseYear() == 1998);
        }
    }

    @Test
    void canSearchByTitleAndReleaseYear() {
        Iterable<Album> albums = albumController.getAlbumsByReleaseYearAndTitle(1998, "Headhunter", PageRequest.of(0, 10));
        List<Album> albumList = new ArrayList<>();
        albums.forEach(albumList::add);

        assert (albumList.size() == 1);
        assert (albumList.get(0).getReleaseYear() == 1998);
        assert (albumList.get(0).getTitle().equals("Headhunter"));
    }

    @Test
    void canGetAlbumById() {
        Optional<Album> album = albumController.getAlbumById("d8611733-81b0-4a55-99cd-e62d8528d51b");

        assert (album.isPresent());
        assert (album.get().getTitle().equals("Under the Stars"));
    }

    @Test
    void findByTitleAggregation() {
        SearchPage<Album> searchPage = albumController.findByTitleWithReleaseYearCount("Exile", PageRequest.of(0, 10));

        assert (searchPage.getSearchHits().stream().count() == 2);

        ElasticsearchAggregations aggregations = (ElasticsearchAggregations) searchPage.getSearchHits().getAggregations();
        assert aggregations != null;
        List<LongTermsBucket> buckets = aggregations.aggregationsAsMap().get("countByReleaseYear").aggregation().getAggregate().lterms().buckets().array();

        assert (buckets.size() == 2);
        assert ((buckets.get(0).key() == 1990 && (buckets.get(0)).docCount() == 1));
        assert ((buckets.get(1).key() == 1997 && (buckets.get(1)).docCount() == 1));
    }

    @Test
    void findAllAggregation() {
        SearchPage<Album> searchPage = albumController.findAllWithReleaseYearCount(PageRequest.of(0, 10));

        ElasticsearchAggregations aggregations = (ElasticsearchAggregations) searchPage.getSearchHits().getAggregations();
        assert aggregations != null;
        List<LongTermsBucket> buckets = aggregations.aggregationsAsMap().get("countByReleaseYear").aggregation().getAggregate().lterms().buckets().array();

        assert (searchPage.getSearchHits().stream().count() == 10);
        assert (searchPage.getSearchHits().getTotalHits() == 1000);
        assert (buckets.size() == 43);
        assert ((buckets.get(1).key() == 1999 && (buckets.get(1)).docCount() == 89));
    }
}

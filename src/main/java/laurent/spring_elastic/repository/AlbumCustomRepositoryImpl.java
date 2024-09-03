package laurent.spring_elastic.repository;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import laurent.spring_elastic.model.Album;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AlbumCustomRepositoryImpl implements AlbumCustomRepository {
    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public SearchPage<Album> findByTitleWithReleaseYearCount(String title, Pageable pageable) {
        Query query = NativeQuery.builder().withAggregation("countByReleaseYear", Aggregation.of(b -> b.terms(t -> t.field("releaseYear").size(1000))))
                .withQuery(q -> q
                        .match(m -> m
                                .field("title")
                                .query(title)
                        ))
                .withPageable(pageable)
                .build();

        SearchHits<Album> searchHits = elasticsearchOperations.search(query, Album.class);

        return SearchHitSupport.searchPageFor(searchHits, pageable);
    }

    @Override
    public SearchPage<Album> findAllWithReleaseYearCount(Pageable pageable) {
        Query query = NativeQuery.builder().withAggregation("countByReleaseYear", Aggregation.of(b -> b.terms(t -> t.field("releaseYear").size(1000))))
                .withPageable(pageable)
                .build();

        SearchHits<Album> searchHits = elasticsearchOperations.search(query, Album.class);

        return SearchHitSupport.searchPageFor(searchHits, pageable);
    }
}

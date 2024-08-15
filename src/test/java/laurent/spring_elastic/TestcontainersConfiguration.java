package laurent.spring_elastic;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;
import java.util.List;

@TestConfiguration(proxyBeanMethods = false)
@ContextConfiguration(classes = TestcontainersConfiguration.class)
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    ElasticsearchContainer elasticsearchContainer() {
        ElasticsearchContainer container = new ElasticsearchContainer(DockerImageName.parse("docker.elastic.co/elasticsearch/elasticsearch:8.8.1"));
        container.getEnvMap().put("xpack.security.enabled", "false");
        container.getEnvMap().put("discovery.type", "single-node");
        List<String> portBindings = new ArrayList<>();
        portBindings.add(("9200:9200"));
        container.setPortBindings(portBindings);
        return container;
    }
}

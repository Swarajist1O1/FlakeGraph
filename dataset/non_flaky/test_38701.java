class DummyClass_38701 {
    @Test
    public void testSslBasic() throws IOException {
        try(ElasticsearchContainer container = new ElasticsearchContainer(ELASTICSEARCH_IMAGE)
                .withCreateContainerCmdModifier(c -> c.withName("elasticsearch"))
                .withFileSystemBind(sslResourceDir, configDir + "/ssl")
                .withEnv("ELASTIC_PASSWORD","elastic")  // boostrap password
                .withEnv("xpack.license.self_generated.type", "trial")
                .withEnv("xpack.security.enabled", "true")
                .withEnv("xpack.security.http.ssl.enabled", "true")
                .withEnv("xpack.security.http.ssl.client_authentication", "optional")
                .withEnv("xpack.security.http.ssl.key", configDir + "/ssl/elasticsearch.key")
                .withEnv("xpack.security.http.ssl.certificate", configDir + "/ssl/elasticsearch.crt")
                .withEnv("xpack.security.http.ssl.certificate_authorities", configDir + "/ssl/cacert.crt")
                .withEnv("xpack.security.transport.ssl.enabled", "true")
                .withEnv("xpack.security.transport.ssl.verification_mode", "certificate")
                .withEnv("xpack.security.transport.ssl.key", configDir + "/ssl/elasticsearch.key")
                .withEnv("xpack.security.transport.ssl.certificate", configDir + "/ssl/elasticsearch.crt")
                .withEnv("xpack.security.transport.ssl.certificate_authorities", configDir + "/ssl/cacert.crt")
                .waitingFor(Wait.forLogMessage(".*(Security is enabled|Active license).*", 1)
                        .withStartupTimeout(Duration.ofMinutes(2)))) {
            container.start();

            ElasticSearchConfig config = new ElasticSearchConfig()
                    .setElasticSearchUrl("https://" + container.getHttpHostAddress())
                    .setIndexName(INDEX)
                    .setUsername("elastic")
                    .setPassword("elastic")
                    .setSsl(new ElasticSearchSslConfig()
                            .setEnabled(true)
                            .setTruststorePath(sslResourceDir + "/truststore.jks")
                            .setTruststorePassword("changeit"));
            ElasticSearchClient client = new ElasticSearchClient(config);
            testIndexExists(client);
        }
    }

}
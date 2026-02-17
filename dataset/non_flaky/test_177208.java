class DummyClass_177208 {
    @Test
    public void normal() {
        try (ClientFactory clientFactory =
                     ClientFactory.builder()
                                  .tlsCustomizer(ctx -> ctx.keyManager(clientCert.certificateFile(),
                                                                       clientCert.privateKeyFile()))
                                  .tlsNoVerify()
                                  .build()) {
            final WebClient client = WebClient.builder(rule.httpsUri())
                                              .factory(clientFactory)
                                              .decorator(LoggingClient.builder().newDecorator())
                                              .build();
            assertThat(client.get("/").aggregate().join().status()).isEqualTo(HttpStatus.OK);
        }
    }

}
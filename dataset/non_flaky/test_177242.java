class DummyClass_177242 {
    @Test
    public void endpointChangePropagatesToListeners() throws Exception {
        final File file = folder.newFile("temp-file.properties");

        PrintWriter printWriter = new PrintWriter(file);
        Properties props = new Properties();
        props.setProperty("serverA.hosts.0", "127.0.0.1:8080");
        props.setProperty("serverA.hosts.1", "127.0.0.1:8081");
        props.store(printWriter, "");
        printWriter.close();

        final PropertiesEndpointGroup propertiesEndpointGroup = PropertiesEndpointGroup.of(
                file.toPath(), "serverA.hosts");
        final EndpointGroup fallbackEndpointGroup = Endpoint.of("127.0.0.1", 8081);
        final EndpointGroup endpointGroup = propertiesEndpointGroup.orElse(fallbackEndpointGroup);

        await().untilAsserted(() -> assertThat(endpointGroup.endpoints()).hasSize(2));

        printWriter = new PrintWriter(file);
        props = new Properties();
        props.store(printWriter, "");
        printWriter.close();

        await().untilAsserted(() -> assertThat(endpointGroup.endpoints()).hasSize(1));

        printWriter = new PrintWriter(file);
        props = new Properties();
        props.setProperty("serverA.hosts.0", "127.0.0.1:8080");
        props.setProperty("serverA.hosts.1", "127.0.0.1:8081");
        props.setProperty("serverA.hosts.2", "127.0.0.1:8082");
        props.store(printWriter, "");
        printWriter.close();

        await().untilAsserted(() -> assertThat(endpointGroup.endpoints()).hasSize(3));
        propertiesEndpointGroup.close();
    }

}
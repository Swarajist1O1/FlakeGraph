class DummyClass_177239 {
    @Test
    public void propertiesFileUpdatesCorrectly() throws Exception {
        final File file = folder.newFile("temp-file.properties");

        PrintWriter printWriter = new PrintWriter(file);
        Properties props = new Properties();
        props.setProperty("serverA.hosts.0", "127.0.0.1:8080");
        props.store(printWriter, "");
        printWriter.close();

        final PropertiesEndpointGroup endpointGroupA = PropertiesEndpointGroup.of(
                file.toPath(), "serverA.hosts");

        await().untilAsserted(() -> assertThat(endpointGroupA.endpoints()).hasSize(1));

        // Update resource
        printWriter = new PrintWriter(file);
        props = new Properties();
        props.setProperty("serverA.hosts.0", "127.0.0.1:8080");
        props.setProperty("serverA.hosts.1", "127.0.0.1:8081");
        props.store(printWriter, "");
        printWriter.close();

        await().untilAsserted(() -> assertThat(endpointGroupA.endpoints()).hasSize(2));

        endpointGroupA.close();
    }

}
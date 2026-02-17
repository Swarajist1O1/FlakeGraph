class DummyClass_177240 {
    @Test
    public void duplicateResourceUrl() throws IOException {
        final File file = folder.newFile("temp-file.properties");
        final PropertiesEndpointGroup propertiesEndpointGroupA =
                PropertiesEndpointGroup.of(file.toPath(), "serverA.hosts");
        final PropertiesEndpointGroup propertiesEndpointGroupB =
                PropertiesEndpointGroup.of(file.toPath(), "serverA.hosts");
        propertiesEndpointGroupA.close();
        propertiesEndpointGroupB.close();
    }

}
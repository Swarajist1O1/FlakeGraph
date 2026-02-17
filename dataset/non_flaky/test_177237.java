class DummyClass_177237 {
    @Test
    public void containsNoHosts() {
        assertThat(PropertiesEndpointGroup.builder(getClass().getClassLoader(),
                                                   "server-list.properties", "serverC.hosts")
                                          .defaultPort(8080)
                                          .build()
                                          .endpoints()).isEmpty();
    }

}
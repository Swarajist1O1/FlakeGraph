class DummyClass_177238 {
    @Test
    public void illegalDefaultPort() {
        assertThatThrownBy(() -> PropertiesEndpointGroup.builder(getClass().getClassLoader(),
                                                                 "server-list.properties", "serverA.hosts")
                                                        .defaultPort(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("defaultPort");
    }

}
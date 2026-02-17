class DummyClass_76705 {
    @Test
    public void testDevServicesProperties() {
        assertThat(context.devServicesProperties()).hasSize(1).containsKey("quarkus.mongodb.connection-string");
    }

}
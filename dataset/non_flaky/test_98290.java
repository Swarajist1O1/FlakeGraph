class DummyClass_98290 {
  @Test
  public void testLoadFromFixture() throws Exception {
    final Container container = objectMapper
        .readValue(fixture("fixtures/container-ports-as-string.json"), Container.class);
    assertThat(container.portsAsString(), is("0.0.0.0:80->88/tcp"));
  }

}
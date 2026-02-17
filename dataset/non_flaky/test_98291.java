class DummyClass_98291 {
  @Test
  public void testLoadFromFixtureMissingPorts() throws Exception {
    final Container container = objectMapper
            .readValue(fixture("fixtures/container-no-ports-or-names.json"), Container.class);
    assertThat(container.id(), is("1009"));
  }

}
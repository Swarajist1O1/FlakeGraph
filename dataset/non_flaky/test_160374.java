class DummyClass_160374 {
  @Test
  public void shouldSerializeWithoutRoot() throws JsonProcessingException {
    final Metadata metadata = new Metadata(INTERCHANGE_VERSION, null);
    assertThat(jsonProvider.objectToPrettyJSON(metadata))
        .isEqualToIgnoringWhitespace("{\"interchange_format_version\":\"5\"}");
  }

}
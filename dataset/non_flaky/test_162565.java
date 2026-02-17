class DummyClass_162565 {
  @Test
  public void shouldSerializeCollection() {
    // given
    List<String> collection = Arrays.asList("one", "two", "three");
    // when
    String serialized = new Serializer().serialize(collection);
    // then
    assertThat(serialized).isEqualTo("[one,two,three]");
  }

}
class DummyClass_162566 {
  @Test
  public void shouldSerializeEmptyCollectionAsNull() {
    // given
    List<String> collection = Collections.emptyList();
    // when
    String serialized = new Serializer().serialize(collection);
    // then
    assertThat(serialized).isNull();
  }

}
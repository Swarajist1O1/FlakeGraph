class DummyClass_162563 {
  @Test
  public void shouldSerializeSimpleString() {
    // given
    // when
    String serialized = new Serializer().serialize("simpleString");
    // then
    assertThat(serialized).isEqualTo("simpleString");
  }

}
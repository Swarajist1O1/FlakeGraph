class DummyClass_30991 {
  @Test
  public void shouldFailDeserializationWithMissingRequiredValues() {
    // given
    final POJO pojo = new POJO();

    final DirectBuffer buf1 = encodeMsgPack((w) -> w.writeMapHeader(0));

    // when
    final Throwable error = catchThrowable(() -> pojo.wrap(buf1));

    // then
    assertThat(error)
        .isInstanceOf(RuntimeException.class)
        .hasMessageContaining("Could not deserialize object")
        .hasCause(new RuntimeException("Property 'enumProp' has no valid value"));
  }

}
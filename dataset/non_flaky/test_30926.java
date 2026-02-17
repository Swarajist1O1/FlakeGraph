class DummyClass_30926 {
  @Test
  public void skipValue() {
    // given
    final ByteArrayBuilder builder = new ByteArrayBuilder();
    given.accept(builder);

    final DirectBuffer buffer = new UnsafeBuffer(builder.value);

    final MsgPackReader reader = new MsgPackReader();
    reader.wrap(buffer, 0, buffer.capacity());

    // when
    reader.skipValue();

    // then
    assertThat(reader.getOffset()).isEqualTo(buffer.capacity());
  }

}
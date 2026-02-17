class DummyClass_30927 {
  @Test
  public void shouldReadToken() {
    // given
    final MsgPackReader reader = new MsgPackReader();
    final ByteArrayBuilder builder = new ByteArrayBuilder();
    given.accept(builder);
    final DirectBuffer buf = new UnsafeBuffer(builder.value);
    reader.wrap(buf, 0, buf.capacity());

    // when
    final MsgPackToken msgPackToken = reader.readToken();

    // then
    assertThat(reader.getOffset()).isEqualTo(buf.capacity());
    assertThat(msgPackToken.getType()).isEqualTo(expectedType);
    assertion.accept(msgPackToken);
  }

}
class DummyClass_30917 {
  @Test
  public void shouldReadMsgPack() {
    // given
    final ByteArrayBuilder builder = new ByteArrayBuilder();
    given.accept(builder);

    final byte[] givenBytes = builder.value;
    final DirectBuffer buf = new UnsafeBuffer(givenBytes);

    final MsgPackReader reader = new MsgPackReader();
    reader.wrap(buf, 0, buf.capacity());

    // when/then
    assertion.accept(reader);
    assertThat(reader.getOffset()).isEqualTo(givenBytes.length);
  }

}
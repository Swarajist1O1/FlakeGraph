class DummyClass_30925 {
  @Test
  public void testWriteMessage() throws Exception {
    // given
    final MsgPackWriter writer = new MsgPackWriter();
    writer.wrap(actualValueBuffer, WRITE_OFFSET);

    final ByteArrayBuilder builder = new ByteArrayBuilder();
    expectedValueWriter.accept(builder);
    final byte[] expectedValue = builder.value;

    // when
    actualValueWriter.accept(writer);

    // then
    assertThat(writer.getOffset()).isEqualTo(WRITE_OFFSET + expectedValue.length);
    assertThatBuffer(actualValueBuffer).hasBytes(expectedValue, WRITE_OFFSET);
  }

}
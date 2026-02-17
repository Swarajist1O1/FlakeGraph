class DummyClass_30928 {
  @Test
  public void shouldNotReadNegativeSize() throws Exception {
    // given
    final MsgPackWriter writer = new MsgPackWriter();
    writer.wrap(actualValueBuffer, WRITE_OFFSET);

    // then
    exception.expect(MsgpackWriterException.class);
    exception.expectMessage(expectedExceptionMessage);

    // when
    codeUnderTest.accept(writer);
  }

}
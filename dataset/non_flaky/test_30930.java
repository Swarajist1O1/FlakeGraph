class DummyClass_30930 {
  @Test
  public void shouldNotReadInvalidSequence() {
    // given
    reader.wrap(NEVER_USED_BUF, 0, NEVER_USED_BUF.capacity());

    // then
    exception.expect(MsgpackReaderException.class);
    exception.expectMessage(expectedExceptionMessage);

    // when
    codeUnderTest.accept(reader);
  }

}
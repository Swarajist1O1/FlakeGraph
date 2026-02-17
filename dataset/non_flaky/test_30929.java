class DummyClass_30929 {
  @Test
  public void shouldNotReadNegativeValue() {
    // given
    final DirectBuffer negativeTestingBuf = new UnsafeBuffer(testingBuf);
    reader.wrap(negativeTestingBuf, 0, negativeTestingBuf.capacity());

    // then
    exception.expect(MsgpackReaderException.class);
    exception.expectMessage(exceptionMessage);

    // when
    codeUnderTest.accept(reader);
  }

}
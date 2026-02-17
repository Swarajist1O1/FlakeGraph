class DummyClass_30920 {
  @Test
  public void testEncodedBinaryValueLength() {
    assertThat(MsgPackWriter.getEncodedBinaryValueLength(0xff)).isEqualTo(2 + 0xff);
    assertThat(MsgPackWriter.getEncodedBinaryValueLength(0xffff)).isEqualTo(3 + 0xffff);
    assertThat(MsgPackWriter.getEncodedBinaryValueLength(0x7fff_fffa)).isEqualTo(5 + 0x7fff_fffa);
  }

}
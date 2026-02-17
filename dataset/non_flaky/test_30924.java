class DummyClass_30924 {
  @Test
  public void testEncodedStringLength() {
    assertThat(MsgPackWriter.getEncodedStringLength(0x1f)).isEqualTo(1 + 0x1f);
    assertThat(MsgPackWriter.getEncodedStringLength(0xff)).isEqualTo(2 + 0xff);
    assertThat(MsgPackWriter.getEncodedStringLength(0xffff)).isEqualTo(3 + 0xffff);
    assertThat(MsgPackWriter.getEncodedStringLength(0x7fff_fffa)).isEqualTo(5 + 0x7fff_fffa);
  }

}
class DummyClass_30923 {
  @Test
  public void testEncodedStringHeaderLength() {
    assertThat(MsgPackWriter.getEncodedStringHeaderLength(0x1f)).isEqualTo(1);
    assertThat(MsgPackWriter.getEncodedStringHeaderLength(0xff)).isEqualTo(2);
    assertThat(MsgPackWriter.getEncodedStringHeaderLength(0xffff)).isEqualTo(3);
    assertThat(MsgPackWriter.getEncodedStringHeaderLength(0x7fff_ffff)).isEqualTo(5);
  }

}
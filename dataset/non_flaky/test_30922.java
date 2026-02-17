class DummyClass_30922 {
  @Test
  public void testEncodedLongValueLength() {
    assertThat(MsgPackWriter.getEncodedLongValueLength(0x7f)).isEqualTo(1);
    assertThat(MsgPackWriter.getEncodedLongValueLength(0xff)).isEqualTo(2);
    assertThat(MsgPackWriter.getEncodedLongValueLength(0xffff)).isEqualTo(3);
    assertThat(MsgPackWriter.getEncodedLongValueLength(0xffff_ffffL)).isEqualTo(5);
    assertThat(MsgPackWriter.getEncodedLongValueLength(0x7fff_ffff_ffff_ffffL)).isEqualTo(9);
    assertThat(MsgPackWriter.getEncodedLongValueLength(-0x20)).isEqualTo(1);
    assertThat(MsgPackWriter.getEncodedLongValueLength(Byte.MIN_VALUE)).isEqualTo(2);
    assertThat(MsgPackWriter.getEncodedLongValueLength(Short.MIN_VALUE)).isEqualTo(3);
    assertThat(MsgPackWriter.getEncodedLongValueLength(Integer.MIN_VALUE)).isEqualTo(5);
    assertThat(MsgPackWriter.getEncodedLongValueLength(Long.MIN_VALUE)).isEqualTo(9);
  }

}
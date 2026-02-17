class DummyClass_30919 {
  @Test
  public void testEncodedArayHeaderLength() {
    assertThat(MsgPackWriter.getEncodedArrayHeaderLenght(0x0f)).isEqualTo(1);
    assertThat(MsgPackWriter.getEncodedArrayHeaderLenght(0xffff)).isEqualTo(3);
    assertThat(MsgPackWriter.getEncodedArrayHeaderLenght(0x7fff_ffff)).isEqualTo(5);
  }

}
class DummyClass_30918 {
  @Test
  public void testEncodedMapHeaderLength() {
    assertThat(MsgPackWriter.getEncodedMapHeaderLenght(0x0f)).isEqualTo(1);
    assertThat(MsgPackWriter.getEncodedMapHeaderLenght(0xffff)).isEqualTo(3);
    assertThat(MsgPackWriter.getEncodedMapHeaderLenght(0x7fff_ffff)).isEqualTo(5);
  }

}
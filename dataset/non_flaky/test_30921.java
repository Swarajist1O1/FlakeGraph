class DummyClass_30921 {
  @Test
  public void testEncodedBooleanValueLength() {
    assertThat(MsgPackWriter.getEncodedBooleanValueLength()).isEqualTo(1);
  }

}
class DummyClass_30981 {
  @Test
  public void shouldReturnDefaultValueForMissingProperty() {
    // given
    final MutableDirectBuffer msgPackBuffer =
        encodeMsgPack(
            (w) -> {
              w.writeMapHeader(1);
              w.writeString(wrapString("noDefaultValueProp"));
              w.writeInteger(123123L);
            });

    final long defaultValue = -1L;
    final DefaultValuesPOJO pojo = new DefaultValuesPOJO(defaultValue);

    // when
    pojo.wrap(msgPackBuffer);

    // then
    assertThat(pojo.getNoDefaultValueProperty()).isEqualTo(123123L);
    assertThat(pojo.getDefaultValueProperty()).isEqualTo(defaultValue);
  }

}
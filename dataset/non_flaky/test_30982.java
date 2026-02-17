class DummyClass_30982 {
  @Test
  public void shouldNotReturnDefaultValueForExistingProperty() {
    // given
    final MutableDirectBuffer msgPackBuffer =
        encodeMsgPack(
            (w) -> {
              w.writeMapHeader(2);
              w.writeString(wrapString("noDefaultValueProp"));
              w.writeInteger(123123L);
              w.writeString(wrapString("defaultValueProp"));
              w.writeInteger(987L);
            });

    final long defaultValue = -1L;
    final DefaultValuesPOJO pojo = new DefaultValuesPOJO(defaultValue);

    // when
    pojo.wrap(msgPackBuffer);

    // then
    assertThat(pojo.getNoDefaultValueProperty()).isEqualTo(123123L);
    assertThat(pojo.getDefaultValueProperty()).isEqualTo(987L);
  }

}
class DummyClass_30983 {
  @Test
  public void shouldReturnDefaultValueAfterReset() {
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
    pojo.wrap(msgPackBuffer);

    // when
    pojo.reset();

    // then
    assertThat(pojo.getDefaultValueProperty()).isEqualTo(defaultValue);
  }

}
class DummyClass_30985 {
  @Test
  public void shouldSupportDefaultValuesForAllPropertyTypes() {
    // given
    final MutableDirectBuffer msgPackBuffer = encodeMsgPack((w) -> w.writeMapHeader(0));

    final MutableDirectBuffer packedMsgPackBuffer =
        encodeMsgPack(
            (w) -> {
              w.writeMapHeader(1);
              w.writeInteger(123L);
              w.writeInteger(456L);
            });

    final AllTypesDefaultValuesPOJO pojo =
        new AllTypesDefaultValuesPOJO(
            POJOEnum.FOO,
            654L,
            123,
            "defaultString",
            packedMsgPackBuffer,
            wrapString("defaultBinary"),
            new POJONested());

    // when
    pojo.wrap(msgPackBuffer);

    // then
    assertThat(pojo.getEnum()).isEqualTo(POJOEnum.FOO);
    assertThat(pojo.getLong()).isEqualTo(654L);
    assertThat(pojo.getInt()).isEqualTo(123);
    assertThatBuffer(pojo.getString()).hasBytes(wrapString("defaultString"));
    assertThatBuffer(pojo.getPacked()).hasBytes(packedMsgPackBuffer);
    assertThatBuffer(pojo.getBinary()).hasBytes(wrapString("defaultBinary"));
    assertThat(pojo.getNestedObject().getLong()).isEqualTo(-1L);
  }

}
class DummyClass_30984 {
  @Test
  public void shouldWriteDefaultValue() {
    // given
    final long defaultValue = -1L;
    final DefaultValuesPOJO pojo = new DefaultValuesPOJO(defaultValue);
    pojo.setNoDefaultValueProperty(123123L);

    final UnsafeBuffer buf = new UnsafeBuffer(new byte[pojo.getLength()]);

    // when
    pojo.write(buf, 0);

    // then
    final MsgPackReader reader = new MsgPackReader();
    reader.wrap(buf, 0, buf.capacity());
    final Map<String, Object> msgPackMap = MsgPackUtil.asMap(buf, 0, buf.capacity());

    assertThat(msgPackMap).hasSize(2);
    assertThat(msgPackMap)
        .contains(entry("noDefaultValueProp", 123123L), entry("defaultValueProp", defaultValue));
  }

}
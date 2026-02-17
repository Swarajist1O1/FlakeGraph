class DummyClass_30954 {
  @Test
  public void shouldConvertJsonSerializableToJson() {
    // given

    // when
    final String json = actualRecordSupplier.get().toJson();

    // then
    JsonUtil.assertEquality(json, expectedJson);
  }

}
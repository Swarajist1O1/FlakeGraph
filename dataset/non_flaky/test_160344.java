class DummyClass_160344 {
  @Test
  public void shouldSerializeObject() throws JsonProcessingException {
    final ValidatorMetricData process =
        new ValidatorMetricData(
            1, UInt64.valueOf(10L).longValue(), "system", 11L, 12L, "teku", "21.8", 3, 4);
    final String data = jsonProvider.objectToJSON(process);
    assertThat(process).isEqualTo(jsonProvider.jsonToObject(data, ValidatorMetricData.class));
  }

}
class DummyClass_160342 {
  @Test
  public void shouldDeserializeObjectFromString() throws JsonProcessingException {
    when(prometheusMock.streamObservations()).thenReturn(getMockObservations().stream());
    final MetricsDataFactory metricsDataFactory = new MetricsDataFactory(prometheusMock);
    final List<BaseMetricData> baseMetricData = metricsDataFactory.getMetricData(timeProvider);
    assertThat(baseMetricData.size()).isEqualTo(3);

    String listOfMetrics = jsonProvider.objectToJSON(baseMetricData);
    DeserializedMetricDataObject[] base =
        jsonProvider.jsonToObject(listOfMetrics, DeserializedMetricDataObject[].class);

    assertThat(base.length).isEqualTo(3);
  }

}
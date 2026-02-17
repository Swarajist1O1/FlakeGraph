class DummyClass_160343 {
  @Test
  public void shouldSerializeObjectFromPrometheusMetricsWithDefaultValues()
      throws JsonProcessingException {
    when(prometheusMock.streamObservations()).thenReturn(new ArrayList<Observation>().stream());
    final MetricsDataFactory metricsDataFactory = new MetricsDataFactory(prometheusMock);

    final List<BaseMetricData> baseMetricData = metricsDataFactory.getMetricData(timeProvider);
    assertThat(baseMetricData.size()).isEqualTo(3);
    final String beaconNode = jsonProvider.objectToJSON(baseMetricData.get(0));
    final String validator = jsonProvider.objectToJSON(baseMetricData.get(1));
    final String system = jsonProvider.objectToJSON(baseMetricData.get(2));

    BeaconNodeMetricData beaconNodeDeserialized =
        jsonProvider.jsonToObject(beaconNode, BeaconNodeMetricData.class);
    ValidatorMetricData validatorDeserialized =
        jsonProvider.jsonToObject(validator, ValidatorMetricData.class);
    SystemMetricData systemDeserialized = jsonProvider.jsonToObject(system, SystemMetricData.class);

    assertThat(baseMetricData.get(0)).isInstanceOf(BeaconNodeMetricData.class);
    assertThat(baseMetricData.get(1)).isInstanceOf(ValidatorMetricData.class);
    assertThat(baseMetricData.get(2)).isInstanceOf(SystemMetricData.class);

    assertThat(baseMetricData.get(0)).isEqualTo(beaconNodeDeserialized);
    assertThat(baseMetricData.get(1)).isEqualTo(validatorDeserialized);
    assertThat(baseMetricData.get(2)).isEqualTo(systemDeserialized);

    assertThat(beaconNodeDeserialized.network_peers_connected).isNull();
    assertThat(validatorDeserialized.validator_total).isNull();
    assertThat(systemDeserialized.cpu_node_system_seconds_total).isNull();
  }

}
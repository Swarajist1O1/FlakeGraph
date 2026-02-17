class DummyClass_60889 {
  @Test
  public void testSuspendResuume() throws IOException
  {
    String supervisorStr = "{\n" +
                           "  \"type\" : \"derivativeDataSource\",\n" +
                           "  \"baseDataSource\": \"wikiticker\",\n" +
                           "  \"dimensionsSpec\":{\n" +
                           "            \"dimensions\" : [\n" +
                           "              \"isUnpatrolled\",\n" +
                           "              \"metroCode\",\n" +
                           "              \"namespace\",\n" +
                           "              \"page\",\n" +
                           "              \"regionIsoCode\",\n" +
                           "              \"regionName\",\n" +
                           "              \"user\"\n" +
                           "            ]\n" +
                           "          },\n" +
                           "    \"metricsSpec\" : [\n" +
                           "        {\n" +
                           "          \"name\" : \"count\",\n" +
                           "          \"type\" : \"count\"\n" +
                           "        },\n" +
                           "        {\n" +
                           "          \"name\" : \"added\",\n" +
                           "          \"type\" : \"longSum\",\n" +
                           "          \"fieldName\" : \"added\"\n" +
                           "        }\n" +
                           "      ],\n" +
                           "  \"tuningConfig\": {\n" +
                           "      \"type\" : \"hadoop\"\n" +
                           "  }\n" +
                           "}";

    MaterializedViewSupervisorSpec spec = objectMapper.readValue(supervisorStr, MaterializedViewSupervisorSpec.class);
    Assert.assertFalse(spec.isSuspended());

    String suspendedSerialized = objectMapper.writeValueAsString(spec.createSuspendedSpec());
    MaterializedViewSupervisorSpec suspendedSpec = objectMapper.readValue(
        suspendedSerialized,
        MaterializedViewSupervisorSpec.class
    );
    Assert.assertTrue(suspendedSpec.isSuspended());

    String runningSerialized = objectMapper.writeValueAsString(spec.createRunningSpec());
    MaterializedViewSupervisorSpec runningSpec = objectMapper.readValue(
        runningSerialized,
        MaterializedViewSupervisorSpec.class
    );
    Assert.assertFalse(runningSpec.isSuspended());
  }

}
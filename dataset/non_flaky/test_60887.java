class DummyClass_60887 {
  @Test
  public void testSupervisorSerialization() throws IOException
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
    MaterializedViewSupervisorSpec expected = new MaterializedViewSupervisorSpec(
        "wikiticker",
        new DimensionsSpec(
            Lists.newArrayList(
                new StringDimensionSchema("isUnpatrolled"),
                new StringDimensionSchema("metroCode"),
                new StringDimensionSchema("namespace"),
                new StringDimensionSchema("page"),
                new StringDimensionSchema("regionIsoCode"),
                new StringDimensionSchema("regionName"),
                new StringDimensionSchema("user")
            ),
            null,
            null
        ),
        new AggregatorFactory[]{
            new CountAggregatorFactory("count"),
            new LongSumAggregatorFactory("added", "added")
        },
        HadoopTuningConfig.makeDefaultTuningConfig(),
        null,
        null,
        null,
        null,
        null,
        false,
        objectMapper,
        null,
        null,
        null,
        null,
        null,
        new MaterializedViewTaskConfig(),
        EasyMock.createMock(AuthorizerMapper.class),
        new NoopChatHandlerProvider(),
        new SupervisorStateManagerConfig()
    );
    MaterializedViewSupervisorSpec spec = objectMapper.readValue(supervisorStr, MaterializedViewSupervisorSpec.class);
    Assert.assertEquals(expected.getBaseDataSource(), spec.getBaseDataSource());
    Assert.assertEquals(expected.getId(), spec.getId());
    Assert.assertEquals(expected.getDataSourceName(), spec.getDataSourceName());
    Assert.assertEquals(expected.getDimensions(), spec.getDimensions());
    Assert.assertEquals(expected.getMetrics(), spec.getMetrics());
  }

}
class DummyClass_60940 {
  @Test
  public void testQuery() throws IOException
  {
    Query<?> query = jsonMapper.readValue(getQueryString(), Query.class);
    Assert.assertThat(query, IsInstanceOf.instanceOf(getExpectedQueryType()));

    List<MapBasedRow> expectedResults = jsonMapper.readValue(getExpectedResultString(), getExpectedResultType());
    Assert.assertNotNull(expectedResults);
    Assert.assertThat(expectedResults, IsInstanceOf.instanceOf(List.class));

    CachingClusteredClient baseClient = new CachingClusteredClient(
        warehouse,
        new TimelineServerView()
        {
          @Override
          public Optional<? extends TimelineLookup<String, ServerSelector>> getTimeline(DataSourceAnalysis analysis)
          {
            return Optional.empty();
          }

          @Override
          public List<ImmutableDruidServer> getDruidServers()
          {
            return null;
          }

}
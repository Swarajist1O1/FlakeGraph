class DummyClass_104612 {
  @Test(dependsOnMethods = "testPartitionMetadata")
  public void testPartitionRouting()
      throws Exception {
    // Query partition 0
    {
      String query = "SELECT COUNT(*) FROM mytable WHERE DestState = 'CA'";
      JsonNode response = postQuery(query);

      String queryToCompare = "SELECT COUNT(*) FROM mytable WHERE DestState BETWEEN 'CA' AND 'CA'";
      JsonNode responseToCompare = postQuery(queryToCompare);

      // Should only query the segments for partition 0
      assertEquals(response.get(MetadataKey.NUM_SEGMENTS_QUERIED.getName()).asInt(), 2);
      assertEquals(responseToCompare.get(MetadataKey.NUM_SEGMENTS_QUERIED.getName()).asInt(), 4);

      assertEquals(response.get("aggregationResults").get(0).get("value").asInt(),
          responseToCompare.get("aggregationResults").get(0).get("value").asInt());
    }

    // Query partition 1
    {
      String query = "SELECT COUNT(*) FROM mytable WHERE DestState = 'FL'";
      JsonNode response = postQuery(query);

      String queryToCompare = "SELECT COUNT(*) FROM mytable WHERE DestState BETWEEN 'FL' AND 'FL'";
      JsonNode responseToCompare = postQuery(queryToCompare);

      // Should only query the segments for partition 1
      assertEquals(response.get(MetadataKey.NUM_SEGMENTS_QUERIED.getName()).asInt(), 2);
      assertEquals(responseToCompare.get(MetadataKey.NUM_SEGMENTS_QUERIED.getName()).asInt(), 4);

      assertEquals(response.get("aggregationResults").get(0).get("value").asInt(),
          responseToCompare.get("aggregationResults").get(0).get("value").asInt());
    }
  }

}
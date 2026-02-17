class DummyClass_104665 {
  @Test
  public void testAggregateMetadataAPI()
      throws IOException {
    JsonNode oneColumnResponse = JsonUtils
        .stringToJsonNode(sendGetRequest(_controllerBaseApiUrl + "/tables/mytable/metadata?columns=DestCityMarketID"));
    assertEquals(oneColumnResponse.get(DISK_SIZE_IN_BYTES_KEY).asInt(), DISK_SIZE_IN_BYTES);
    assertEquals(oneColumnResponse.get(NUM_SEGMENTS_KEY).asInt(), NUM_SEGMENTS);
    assertEquals(oneColumnResponse.get(NUM_ROWS_KEY).asInt(), NUM_ROWS);
    assertEquals(oneColumnResponse.get(COLUMN_LENGTH_MAP_KEY).size(), 1);
    assertEquals(oneColumnResponse.get(COLUMN_CARDINALITY_MAP_KEY).size(), 1);

    JsonNode threeColumnsResponse = JsonUtils.stringToJsonNode(sendGetRequest(_controllerBaseApiUrl
        + "/tables/mytable/metadata?columns=DivActualElapsedTime&columns=CRSElapsedTime&columns=OriginStateName"));
    assertEquals(threeColumnsResponse.get(DISK_SIZE_IN_BYTES_KEY).asInt(), DISK_SIZE_IN_BYTES);
    assertEquals(threeColumnsResponse.get(NUM_SEGMENTS_KEY).asInt(), NUM_SEGMENTS);
    assertEquals(threeColumnsResponse.get(NUM_ROWS_KEY).asInt(), NUM_ROWS);
    assertEquals(threeColumnsResponse.get(COLUMN_LENGTH_MAP_KEY).size(), 3);
    assertEquals(threeColumnsResponse.get(COLUMN_CARDINALITY_MAP_KEY).size(), 3);

    JsonNode zeroColumnResponse =
        JsonUtils.stringToJsonNode(sendGetRequest(_controllerBaseApiUrl + "/tables/mytable/metadata"));
    assertEquals(zeroColumnResponse.get(DISK_SIZE_IN_BYTES_KEY).asInt(), DISK_SIZE_IN_BYTES);
    assertEquals(zeroColumnResponse.get(NUM_SEGMENTS_KEY).asInt(), NUM_SEGMENTS);
    assertEquals(zeroColumnResponse.get(NUM_ROWS_KEY).asInt(), NUM_ROWS);
    assertEquals(zeroColumnResponse.get(COLUMN_LENGTH_MAP_KEY).size(), 0);
    assertEquals(zeroColumnResponse.get(COLUMN_CARDINALITY_MAP_KEY).size(), 0);

    JsonNode allColumnResponse =
        JsonUtils.stringToJsonNode(sendGetRequest(_controllerBaseApiUrl + "/tables/mytable/metadata?columns=*"));
    assertEquals(allColumnResponse.get(DISK_SIZE_IN_BYTES_KEY).asInt(), DISK_SIZE_IN_BYTES);
    assertEquals(allColumnResponse.get(NUM_SEGMENTS_KEY).asInt(), NUM_SEGMENTS);
    assertEquals(allColumnResponse.get(NUM_ROWS_KEY).asInt(), NUM_ROWS);
    assertEquals(allColumnResponse.get(COLUMN_LENGTH_MAP_KEY).size(), 82);
    assertEquals(allColumnResponse.get(COLUMN_CARDINALITY_MAP_KEY).size(), 82);

    allColumnResponse = JsonUtils.stringToJsonNode(sendGetRequest(
        _controllerBaseApiUrl + "/tables/mytable/metadata?columns=CRSElapsedTime&columns=*&columns=OriginStateName"));
    assertEquals(allColumnResponse.get(DISK_SIZE_IN_BYTES_KEY).asInt(), DISK_SIZE_IN_BYTES);
    assertEquals(allColumnResponse.get(NUM_SEGMENTS_KEY).asInt(), NUM_SEGMENTS);
    assertEquals(allColumnResponse.get(NUM_ROWS_KEY).asInt(), NUM_ROWS);
    assertEquals(allColumnResponse.get(COLUMN_LENGTH_MAP_KEY).size(), 82);
    assertEquals(allColumnResponse.get(COLUMN_CARDINALITY_MAP_KEY).size(), 82);
  }

}
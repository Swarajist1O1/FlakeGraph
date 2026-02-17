class DummyClass_104687 {
  @Test
  public void testJsonPathQueries()
      throws Exception {
    // Selection only
    String query = "SELECT stringKeyMapStr FROM " + getTableName();
    JsonNode pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    JsonNode selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 10);
    for (int i = 0; i < 10; i++) {
      assertEquals(selectionResults.get(i).get(0).textValue(), String.format("{\"k1\":%d,\"k2\":100%d}", i, i));
    }
    query = "SELECT jsonExtractScalar(stringKeyMapStr, '$.k1', 'INT') FROM " + getTableName();
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 10);
    for (int i = 0; i < 10; i++) {
      assertEquals(Integer.parseInt(selectionResults.get(i).get(0).textValue()), i);
    }
    query = "SELECT jsonExtractScalar(intKeyMapStr, '$.95', 'INT') FROM " + getTableName();
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 10);
    for (int i = 0; i < 10; i++) {
      assertEquals(Integer.parseInt(selectionResults.get(i).get(0).textValue()), i);
    }

    // Selection order-by
    query = "SELECT jsonExtractScalar(stringKeyMapStr, '$.k2', 'INT') FROM " + getTableName()
        + " ORDER BY jsonExtractScalar(stringKeyMapStr, '$.k1', 'INT')";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 10);
    for (int i = 0; i < 10; i++) {
      assertEquals(Integer.parseInt(selectionResults.get(i).get(0).textValue()), NUM_DOCS + i);
    }
    query = "SELECT jsonExtractScalar(intKeyMapStr, '$.717', 'INT') FROM " + getTableName()
        + " ORDER BY jsonExtractScalar(intKeyMapStr, '$.95', 'INT')";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 10);
    for (int i = 0; i < 10; i++) {
      assertEquals(Integer.parseInt(selectionResults.get(i).get(0).textValue()), NUM_DOCS + i);
    }

    // Aggregation only
    query = "SELECT MAX(jsonExtractScalar(stringKeyMapStr, '$.k1', 'INT')) FROM " + getTableName();
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    JsonNode aggregationResult = pinotResponse.get("aggregationResults").get(0).get("value");
    assertEquals((int) Double.parseDouble(aggregationResult.textValue()), NUM_DOCS - 1);
    query = "SELECT MAX(jsonExtractScalar(intKeyMapStr, '$.95', 'INT')) FROM " + getTableName();
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    aggregationResult = pinotResponse.get("aggregationResults").get(0).get("value");
    assertEquals((int) Double.parseDouble(aggregationResult.textValue()), NUM_DOCS - 1);

    // Aggregation group-by
    query = "SELECT MIN(jsonExtractScalar(stringKeyMapStr, '$.k2', 'INT')) FROM " + getTableName()
        + " GROUP BY jsonExtractScalar(stringKeyMapStr, '$.k1', 'INT')";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    JsonNode groupByResults = pinotResponse.get("aggregationResults").get(0).get("groupByResult");
    assertEquals(groupByResults.size(), 10);
    for (int i = 0; i < 10; i++) {
      JsonNode groupByResult = groupByResults.get(i);
      assertEquals(Integer.parseInt(groupByResult.get("group").get(0).asText()), i);
      assertEquals((int) Double.parseDouble(groupByResult.get("value").asText()), NUM_DOCS + i);
    }
    query = "SELECT MIN(jsonExtractScalar(intKeyMapStr, '$.717', 'INT')) FROM " + getTableName()
        + " GROUP BY jsonExtractScalar(intKeyMapStr, '$.95', 'INT')";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    groupByResults = pinotResponse.get("aggregationResults").get(0).get("groupByResult");
    assertEquals(groupByResults.size(), 10);
    for (int i = 0; i < 10; i++) {
      JsonNode groupByResult = groupByResults.get(i);
      assertEquals(Integer.parseInt(groupByResult.get("group").get(0).asText()), i);
      assertEquals((int) Double.parseDouble(groupByResult.get("value").asText()), NUM_DOCS + i);
    }

    // Filter
    query = "SELECT jsonExtractScalar(stringKeyMapStr, '$.k2', 'INT') FROM " + getTableName()
        + " WHERE jsonExtractScalar(stringKeyMapStr, '$.k1', 'INT') = 25";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 1);
    assertEquals(Integer.parseInt(selectionResults.get(0).get(0).textValue()), NUM_DOCS + 25);
    query = "SELECT jsonExtractScalar(intKeyMapStr, '$.717', 'INT') FROM " + getTableName()
        + " WHERE jsonExtractScalar(intKeyMapStr, '$.95', 'INT') = 25";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 1);
    assertEquals(Integer.parseInt(selectionResults.get(0).get(0).textValue()), NUM_DOCS + 25);

    // Filter on non-existing key
    query = "SELECT jsonExtractScalar(stringKeyMapStr, '$.k2', 'INT') FROM " + getTableName()
        + " WHERE jsonExtractScalar(stringKeyMapStr, '$.k3', 'INT_ARRAY') = 25";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 0);
    query = "SELECT jsonExtractScalar(intKeyMapStr, '$.717', 'INT') FROM " + getTableName()
        + " WHERE jsonExtractScalar(intKeyMapStr, '$.123', 'INT_ARRAY') = 25";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 0);

    // Select non-existing key (illegal query)
    query = "SELECT jsonExtractScalar(stringKeyMapStr, '$.k3', 'INT') FROM " + getTableName();
    pinotResponse = postQuery(query);
    assertNotEquals(pinotResponse.get("exceptions").size(), 0);
    query = "SELECT jsonExtractScalar(stringKeyMapStr, '$.123', 'INT') FROM " + getTableName();
    pinotResponse = postQuery(query);
    assertNotEquals(pinotResponse.get("exceptions").size(), 0);

    // Select non-existing key with default value
    query = "SELECT jsonExtractScalar(stringKeyMapStr, '$.k3', 'INT', '0') FROM " + getTableName();
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    query = "SELECT jsonExtractScalar(stringKeyMapStr, '$.123', 'INT', '0') FROM " + getTableName();
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);

    // Select non-existing key with proper filter
    query = "SELECT jsonExtractScalar(intKeyMapStr, '$.123', 'INT') FROM " + getTableName()
        + " WHERE jsonExtractKey(intKeyMapStr, '$.*') = \"$['123']\"";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 0);
    query = "SELECT jsonExtractScalar(stringKeyMapStr, '$.k3', 'INT') FROM " + getTableName()
        + " WHERE jsonExtractKey(stringKeyMapStr, '$.*') = \"$['k3']\"";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 0);
  }

}
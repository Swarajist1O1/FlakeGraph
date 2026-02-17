class DummyClass_104688 {
  @Test
  public void testQueries()
      throws Exception {
    // Selection only
    String query = "SELECT mapValue(stringKeyMap__KEYS, 'k1', stringKeyMap__VALUES) FROM " + getTableName();
    JsonNode pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    JsonNode selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 10);
    for (int i = 0; i < 10; i++) {
      assertEquals(Integer.parseInt(selectionResults.get(i).get(0).textValue()), i);
    }
    query = "SELECT mapValue(intKeyMap__KEYS, 95, intKeyMap__VALUES) FROM " + getTableName();
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 10);
    for (int i = 0; i < 10; i++) {
      assertEquals(Integer.parseInt(selectionResults.get(i).get(0).textValue()), i);
    }

    // Selection order-by
    query = "SELECT mapValue(stringKeyMap__KEYS, 'k2', stringKeyMap__VALUES) FROM " + getTableName()
        + " ORDER BY mapValue(stringKeyMap__KEYS, 'k1', stringKeyMap__VALUES)";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 10);
    for (int i = 0; i < 10; i++) {
      assertEquals(Integer.parseInt(selectionResults.get(i).get(0).textValue()), NUM_DOCS + i);
    }
    query = "SELECT mapValue(intKeyMap__KEYS, 717, intKeyMap__VALUES) FROM " + getTableName()
        + " ORDER BY mapValue(intKeyMap__KEYS, 95, intKeyMap__VALUES)";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 10);
    for (int i = 0; i < 10; i++) {
      assertEquals(Integer.parseInt(selectionResults.get(i).get(0).textValue()), NUM_DOCS + i);
    }

    // Aggregation only
    query = "SELECT MAX(mapValue(stringKeyMap__KEYS, 'k1', stringKeyMap__VALUES)) FROM " + getTableName();
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    JsonNode aggregationResult = pinotResponse.get("aggregationResults").get(0).get("value");
    assertEquals((int) Double.parseDouble(aggregationResult.textValue()), NUM_DOCS - 1);
    query = "SELECT MAX(mapValue(intKeyMap__KEYS, 95, intKeyMap__VALUES)) FROM " + getTableName();
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    aggregationResult = pinotResponse.get("aggregationResults").get(0).get("value");
    assertEquals((int) Double.parseDouble(aggregationResult.textValue()), NUM_DOCS - 1);

    // Aggregation group-by
    query = "SELECT MIN(mapValue(stringKeyMap__KEYS, 'k2', stringKeyMap__VALUES)) FROM " + getTableName()
        + " GROUP BY mapValue(stringKeyMap__KEYS, 'k1', stringKeyMap__VALUES)";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    JsonNode groupByResults = pinotResponse.get("aggregationResults").get(0).get("groupByResult");
    assertEquals(groupByResults.size(), 10);
    for (int i = 0; i < 10; i++) {
      JsonNode groupByResult = groupByResults.get(i);
      assertEquals(Integer.parseInt(groupByResult.get("group").get(0).asText()), i);
      assertEquals((int) Double.parseDouble(groupByResult.get("value").asText()), NUM_DOCS + i);
    }
    query = "SELECT MIN(mapValue(intKeyMap__KEYS, 717, intKeyMap__VALUES)) FROM " + getTableName()
        + " GROUP BY mapValue(intKeyMap__KEYS, 95, intKeyMap__VALUES)";
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
    query = "SELECT mapValue(stringKeyMap__KEYS, 'k2', stringKeyMap__VALUES) FROM " + getTableName()
        + " WHERE mapValue(stringKeyMap__KEYS, 'k1', stringKeyMap__VALUES) = 25";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 1);
    assertEquals(Integer.parseInt(selectionResults.get(0).get(0).textValue()), NUM_DOCS + 25);
    query = "SELECT mapValue(intKeyMap__KEYS, 717, intKeyMap__VALUES) FROM " + getTableName()
        + " WHERE mapValue(intKeyMap__KEYS, 95, intKeyMap__VALUES) = 25";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 1);
    assertEquals(Integer.parseInt(selectionResults.get(0).get(0).textValue()), NUM_DOCS + 25);

    // Filter on non-existing key
    query = "SELECT mapValue(stringKeyMap__KEYS, 'k2', stringKeyMap__VALUES) FROM " + getTableName()
        + " WHERE mapValue(stringKeyMap__KEYS, 'k3', stringKeyMap__VALUES) = 25";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 0);
    query = "SELECT mapValue(intKeyMap__KEYS, 717, intKeyMap__VALUES) FROM " + getTableName()
        + " WHERE mapValue(intKeyMap__KEYS, 123, intKeyMap__VALUES) = 25";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 0);

    // Select non-existing key (illegal query)
    query = "SELECT mapValue(stringKeyMap__KEYS, 'k3', stringKeyMap__VALUES) FROM " + getTableName();
    pinotResponse = postQuery(query);
    assertNotEquals(pinotResponse.get("exceptions").size(), 0);
    query = "SELECT mapValue(stringKeyMap__KEYS, 123, stringKeyMap__VALUES) FROM " + getTableName();
    pinotResponse = postQuery(query);
    assertNotEquals(pinotResponse.get("exceptions").size(), 0);

    // Select non-existing key with proper filter
    query = "SELECT mapValue(stringKeyMap__KEYS, 'k3', stringKeyMap__VALUES) FROM " + getTableName()
        + " WHERE stringKeyMap__KEYS = 'k3'";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 0);
    query = "SELECT mapValue(intKeyMap__KEYS, 123, intKeyMap__VALUES) FROM " + getTableName()
        + " WHERE stringKeyMap__KEYS = 123";
    pinotResponse = postQuery(query);
    assertEquals(pinotResponse.get("exceptions").size(), 0);
    selectionResults = pinotResponse.get("selectionResults").get("results");
    assertEquals(selectionResults.size(), 0);
  }

}
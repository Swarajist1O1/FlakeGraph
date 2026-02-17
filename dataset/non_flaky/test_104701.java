class DummyClass_104701 {
  @Test
  public void testSegmentUploadDownload()
      throws Exception {
    final Request query = new Request("sql", "SELECT count(*) FROM " + getTableName());

    ResultSetGroup resultBeforeOffline = getPinotConnection().execute(query);
    Assert.assertTrue(resultBeforeOffline.getResultSet(0).getLong(0) > 0);

    // schedule offline segment generation
    Assert.assertNotNull(_controllerStarter.getTaskManager().scheduleTasks());

    // wait for offline segments
    JsonNode offlineSegments = TestUtils.waitForResult(() -> {
      JsonNode segmentSets = JsonUtils.stringToJsonNode(
          sendGetRequest(_controllerRequestURLBuilder.forSegmentListAPI(getTableName()), AUTH_HEADER));
      JsonNode currentOfflineSegments =
          new IntRange(0, segmentSets.size()).stream().map(segmentSets::get).filter(s -> s.has("OFFLINE"))
              .map(s -> s.get("OFFLINE")).findFirst().get();
      Assert.assertFalse(currentOfflineSegments.isEmpty());
      return currentOfflineSegments;
    }, 30000);

    // Verify constant row count
    ResultSetGroup resultAfterOffline = getPinotConnection().execute(query);
    Assert.assertEquals(resultBeforeOffline.getResultSet(0).getLong(0), resultAfterOffline.getResultSet(0).getLong(0));

    // download and sanity-check size of offline segment(s)
    for (int i = 0; i < offlineSegments.size(); i++) {
      String segment = offlineSegments.get(i).asText();
      Assert.assertTrue(
          sendGetRequest(_controllerRequestURLBuilder.forSegmentDownload(getTableName(), segment), AUTH_HEADER).length()
              > 200000); // download segment
    }
  }

}
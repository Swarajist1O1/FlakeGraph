class DummyClass_104633 {
  @Test
  public void testUploadSegmentRefreshOnly()
      throws Exception {
    TableConfig segmentUploadTestTableConfig =
        new TableConfigBuilder(TableType.OFFLINE).setTableName(SEGMENT_UPLOAD_TEST_TABLE).setSchemaName(getSchemaName())
            .setTimeColumnName(getTimeColumnName()).setSortedColumn(getSortedColumn())
            .setInvertedIndexColumns(getInvertedIndexColumns()).setNoDictionaryColumns(getNoDictionaryColumns())
            .setRangeIndexColumns(getRangeIndexColumns()).setBloomFilterColumns(getBloomFilterColumns())
            .setFieldConfigList(getFieldConfigs()).setNumReplicas(getNumReplicas())
            .setSegmentVersion(getSegmentVersion())
            .setLoadMode(getLoadMode()).setTaskConfig(getTaskConfig()).setBrokerTenant(getBrokerTenant())
            .setServerTenant(getServerTenant()).setIngestionConfig(getIngestionConfig())
            .setNullHandlingEnabled(getNullHandlingEnabled()).build();
    addTableConfig(segmentUploadTestTableConfig);
    String offlineTableName = segmentUploadTestTableConfig.getTableName();
    File[] segmentTarFiles = _tarDir.listFiles();
    assertNotNull(segmentTarFiles);
    int numSegments = segmentTarFiles.length;
    assertTrue(numSegments > 0);
    List<Header> headers = new ArrayList<>();
    headers.add(new BasicHeader(FileUploadDownloadClient.CustomHeaders.REFRESH_ONLY, "true"));
    List<NameValuePair> parameters = new ArrayList<>();
    NameValuePair tableNameParameter = new BasicNameValuePair(FileUploadDownloadClient.QueryParameters.TABLE_NAME,
        TableNameBuilder.extractRawTableName(offlineTableName));
    parameters.add(tableNameParameter);

    URI uploadSegmentHttpURI = FileUploadDownloadClient.getUploadSegmentHttpURI(LOCAL_HOST, _controllerPort);
    try (FileUploadDownloadClient fileUploadDownloadClient = new FileUploadDownloadClient()) {
      // Refresh non-existing segment
      File segmentTarFile = segmentTarFiles[0];
      try {
        fileUploadDownloadClient
            .uploadSegment(uploadSegmentHttpURI, segmentTarFile.getName(), segmentTarFile, headers, parameters,
                FileUploadDownloadClient.DEFAULT_SOCKET_TIMEOUT_MS);
        fail();
      } catch (HttpErrorStatusException e) {
        assertEquals(e.getStatusCode(), HttpStatus.SC_GONE);
        assertTrue(_helixResourceManager.getSegmentsZKMetadata(SEGMENT_UPLOAD_TEST_TABLE).isEmpty());
      }

      // Upload segment
      SimpleHttpResponse response = fileUploadDownloadClient
          .uploadSegment(uploadSegmentHttpURI, segmentTarFile.getName(), segmentTarFile, null, parameters,
              FileUploadDownloadClient.DEFAULT_SOCKET_TIMEOUT_MS);
      assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
      System.out.println(response.getResponse());
      List<SegmentZKMetadata> segmentsZKMetadata = _helixResourceManager.getSegmentsZKMetadata(offlineTableName);
      assertEquals(segmentsZKMetadata.size(), 1);

      // Refresh existing segment
      response = fileUploadDownloadClient
          .uploadSegment(uploadSegmentHttpURI, segmentTarFile.getName(), segmentTarFile, headers, parameters,
              FileUploadDownloadClient.DEFAULT_SOCKET_TIMEOUT_MS);
      assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
      segmentsZKMetadata = _helixResourceManager.getSegmentsZKMetadata(offlineTableName);
      assertEquals(segmentsZKMetadata.size(), 1);
      assertNotEquals(segmentsZKMetadata.get(0).getRefreshTime(), Long.MIN_VALUE);
    }
    dropOfflineTable(SEGMENT_UPLOAD_TEST_TABLE);
  }

}
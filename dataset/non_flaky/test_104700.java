class DummyClass_104700 {
  @Test
  public void testFileBasedSegmentWriterAndDefaultUploader()
      throws Exception {

    TableConfig offlineTableConfig = createOfflineTableConfig();
    addTableConfig(offlineTableConfig);

    SegmentWriter segmentWriter = new FileBasedSegmentWriter();
    segmentWriter.init(offlineTableConfig, _schema);
    SegmentUploader segmentUploader = new SegmentUploaderDefault();
    segmentUploader.init(offlineTableConfig);

    GenericRow reuse = new GenericRow();
    long totalDocs = 0;
    for (int i = 0; i < 3; i++) {
      AvroRecordReader avroRecordReader = new AvroRecordReader();
      avroRecordReader.init(_avroFiles.get(i), null, null);

      long numDocsInSegment = 0;
      while (avroRecordReader.hasNext()) {
        avroRecordReader.next(reuse);
        segmentWriter.collect(reuse);
        numDocsInSegment++;
        totalDocs++;
      }
      // flush to segment
      URI segmentTarURI = segmentWriter.flush();
      // upload
      segmentUploader.uploadSegment(segmentTarURI, null);

      // check num segments
      Assert.assertEquals(getNumSegments(), i + 1);
      // check numDocs in latest segment
      Assert.assertEquals(getNumDocsInLatestSegment(), numDocsInSegment);
      // check totalDocs in query
      checkTotalDocsInQuery(totalDocs);
    }
    segmentWriter.close();

    dropAllSegments(_tableNameWithType, TableType.OFFLINE);
    checkNumSegments(0);

    // upload all together using dir
    segmentUploader.uploadSegmentsFromDir(_tarDir.toURI(), null);
    // check num segments
    Assert.assertEquals(getNumSegments(), 3);
    // check totalDocs in query
    checkTotalDocsInQuery(totalDocs);

    dropOfflineTable(_tableNameWithType);
  }

}
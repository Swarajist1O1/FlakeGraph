class DummyClass_96942 {
  @Test
  public void testWriter() throws IOException {
    LOG.debug("Writing some records to a SortedKeyValueFile...");

    Configuration conf = new Configuration();
    SortedKeyValueFile.Writer.Options options = new SortedKeyValueFile.Writer.Options()
        .withKeySchema(Schema.create(Schema.Type.STRING))
        .withValueSchema(Schema.create(Schema.Type.STRING))
        .withConfiguration(conf)
        .withPath(new Path(mTempDir.getRoot().getPath(), "myfile"))
        .withIndexInterval(2);  // Index every other record.

    SortedKeyValueFile.Writer<CharSequence, CharSequence> writer
        = new SortedKeyValueFile.Writer<>(options);

    try {
      writer.append("apple", "Apple");  // Will be indexed.
      writer.append("banana", "Banana");
      writer.append("carrot", "Carrot");  // Will be indexed.
      writer.append("durian", "Durian");
    } finally {
      writer.close();
    }


    LOG.debug("Checking the generated directory...");
    File directory = new File(mTempDir.getRoot().getPath(), "myfile");
    assertTrue(directory.exists());


    LOG.debug("Checking the generated index file...");
    File indexFile = new File(directory, SortedKeyValueFile.INDEX_FILENAME);
    DatumReader<GenericRecord> indexReader = new GenericDatumReader<>(
        AvroKeyValue.getSchema(options.getKeySchema(), Schema.create(Schema.Type.LONG)));
    FileReader<GenericRecord> indexFileReader = DataFileReader.openReader(indexFile, indexReader);

    List<AvroKeyValue<CharSequence, Long>> indexRecords
        = new ArrayList<>();
    try {
      for (GenericRecord indexRecord : indexFileReader) {
        indexRecords.add(new AvroKeyValue<>(indexRecord));
      }
    } finally {
      indexFileReader.close();
    }

    assertEquals(2, indexRecords.size());
    assertEquals("apple", indexRecords.get(0).getKey().toString());
    LOG.debug("apple's position in the file: " + indexRecords.get(0).getValue());
    assertEquals("carrot", indexRecords.get(1).getKey().toString());
    LOG.debug("carrot's position in the file: " + indexRecords.get(1).getValue());

    LOG.debug("Checking the generated data file...");
    File dataFile = new File(directory, SortedKeyValueFile.DATA_FILENAME);
    DatumReader<GenericRecord> dataReader = new GenericDatumReader<>(
        AvroKeyValue.getSchema(options.getKeySchema(), options.getValueSchema()));
    DataFileReader<GenericRecord> dataFileReader
        = new DataFileReader<>(dataFile, dataReader);

    try {
      dataFileReader.seek(indexRecords.get(0).getValue());
      assertTrue(dataFileReader.hasNext());
      AvroKeyValue<CharSequence, CharSequence> appleRecord
          = new AvroKeyValue<>(dataFileReader.next());
      assertEquals("apple", appleRecord.getKey().toString());
      assertEquals("Apple", appleRecord.getValue().toString());

      dataFileReader.seek(indexRecords.get(1).getValue());
      assertTrue(dataFileReader.hasNext());
      AvroKeyValue<CharSequence, CharSequence> carrotRecord
          = new AvroKeyValue<>(dataFileReader.next());
      assertEquals("carrot", carrotRecord.getKey().toString());
      assertEquals("Carrot", carrotRecord.getValue().toString());

      assertTrue(dataFileReader.hasNext());
      AvroKeyValue<CharSequence, CharSequence> durianRecord
          = new AvroKeyValue<>(dataFileReader.next());
      assertEquals("durian", durianRecord.getKey().toString());
      assertEquals("Durian", durianRecord.getValue().toString());
    } finally {
      dataFileReader.close();
    }
  }

}
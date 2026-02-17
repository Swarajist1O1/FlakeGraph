class DummyClass_96940 {
  @Test
  public void testDeflateClassCodec() throws IOException {
    Configuration conf = new Configuration();
    Path myfile = new Path(mTempDir.getRoot().getPath(), "myfile");
    Schema key = Schema.create(Schema.Type.STRING);
    Schema value = Schema.create(Schema.Type.STRING);
    Schema recordSchema = AvroKeyValue.getSchema(key, value);
    DatumReader<GenericRecord> datumReader = SpecificData.get().createDatumReader(recordSchema);
    DataFileReader<GenericRecord> reader;

    LOG.debug("Using CodecFactory.deflateCodec() for a SortedKeyValueFile...");
    SortedKeyValueFile.Writer.Options options = new SortedKeyValueFile.Writer.Options()
        .withKeySchema(key)
        .withValueSchema(value)
        .withConfiguration(conf)
        .withPath(myfile)
        .withCodec(CodecFactory.deflateCodec(9));

    SortedKeyValueFile.Writer<CharSequence, CharSequence> writer =
        new SortedKeyValueFile.Writer<>(options);
    writer.close();

    reader = new DataFileReader<>(
        new FsInput(new Path(myfile, SortedKeyValueFile.DATA_FILENAME), conf),
        datumReader);

    assertEquals("deflate", reader.getMetaString("avro.codec"));
    reader.close();
  }

}
class DummyClass_96939 {
  @Test
  public void testNamedCodecs() throws IOException {
    Configuration conf = new Configuration();
    Path myfile = new Path(mTempDir.getRoot().getPath(), "myfile");
    Schema key = Schema.create(Schema.Type.STRING);
    Schema value = Schema.create(Schema.Type.STRING);
    Schema recordSchema = AvroKeyValue.getSchema(key, value);
    DatumReader<GenericRecord> datumReader = SpecificData.get().createDatumReader(recordSchema);
    DataFileReader<GenericRecord> reader;

    SortedKeyValueFile.Writer.Options options = new SortedKeyValueFile.Writer.Options()
        .withKeySchema(key)
        .withValueSchema(value)
        .withConfiguration(conf)
        .withPath(myfile);

    SortedKeyValueFile.Writer<CharSequence, CharSequence> writer;

    for(String codec : new String[]{"null", "deflate", "snappy", "bzip2"}) {
        LOG.debug("Using " + codec + "codec for a SortedKeyValueFile...");

        options.withCodec(codec);

        writer = new SortedKeyValueFile.Writer<>(options);
        writer.close();

        reader = new DataFileReader<>(
            new FsInput(new Path(myfile, SortedKeyValueFile.DATA_FILENAME), conf),
            datumReader);

        assertEquals(codec, reader.getMetaString("avro.codec"));
        reader.close();
    }
  }

}
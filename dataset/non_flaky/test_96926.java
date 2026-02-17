class DummyClass_96926 {
  @Test
  public void testAvroTextRecordWriter() throws Exception {
    File file = new File(tmpFolder.getRoot().getPath(), "writer");
    Schema schema = Schema.create(Schema.Type.BYTES);
    DatumWriter<ByteBuffer> datumWriter =
      new GenericDatumWriter<>(schema);
    DataFileWriter<ByteBuffer> fileWriter =
      new DataFileWriter<>(datumWriter);
    fileWriter.create(schema, file);
    RecordWriter<Object, Object> rw = new AvroTextOutputFormat<>()
      .new AvroTextRecordWriter(fileWriter, "\t".getBytes(UTF8));

    rw.write(null, null);
    rw.write(null, NullWritable.get());
    rw.write(NullWritable.get(), null);
    rw.write(NullWritable.get(), NullWritable.get());

    rw.write("k1", null);
    rw.write("k2", NullWritable.get());

    rw.write(null, "v1");
    rw.write(NullWritable.get(), "v2");

    rw.write("k3", "v3");
    rw.write(new Text("k4"), new Text("v4"));

    rw.close(null);

    DatumReader<ByteBuffer> reader = new GenericDatumReader<>();
    DataFileReader<ByteBuffer> fileReader =
      new DataFileReader<>(file, reader);
    assertEquals("k1", asString(fileReader.next()));
    assertEquals("k2", asString(fileReader.next()));
    assertEquals("v1", asString(fileReader.next()));
    assertEquals("v2", asString(fileReader.next()));
    assertEquals("k3\tv3", asString(fileReader.next()));
    assertEquals("k4\tv4", asString(fileReader.next()));
    assertFalse("End", fileReader.hasNext());
  }

}
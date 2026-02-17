class DummyClass_96957 {
  @Test
  public void testGetDeserializerForKey() throws IOException {
    // Set the reader schema in the job configuration.
    Schema readerSchema = Schema.create(Schema.Type.STRING);
    Job job = new Job();
    AvroJob.setMapOutputKeySchema(job, readerSchema);

    // Get a deserializer from the configuration.
    AvroSerialization serialization
        = ReflectionUtils.newInstance(AvroSerialization.class, job.getConfiguration());
    @SuppressWarnings("unchecked")
    Deserializer<AvroWrapper> deserializer = serialization.getDeserializer(AvroKey.class);
    assertTrue(deserializer instanceof AvroKeyDeserializer);
    AvroKeyDeserializer avroDeserializer = (AvroKeyDeserializer) deserializer;

    // Check that the reader schema is set correctly on the deserializer.
    assertEquals(readerSchema, avroDeserializer.getReaderSchema());
  }

}
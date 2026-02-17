class DummyClass_96955 {
  @Test
  public void testGetSerializerForKey() throws IOException {
    // Set the writer schema in the job configuration.
    Schema writerSchema = Schema.create(Schema.Type.STRING);
    Job job = new Job();
    AvroJob.setMapOutputKeySchema(job, writerSchema);

    // Get a serializer from the configuration.
    AvroSerialization serialization
        = ReflectionUtils.newInstance(AvroSerialization.class, job.getConfiguration());
    @SuppressWarnings("unchecked")
    Serializer<AvroWrapper> serializer = serialization.getSerializer(AvroKey.class);
    assertTrue(serializer instanceof AvroSerializer);
    AvroSerializer avroSerializer = (AvroSerializer) serializer;

    // Check that the writer schema is set correctly on the serializer.
    assertEquals(writerSchema, avroSerializer.getWriterSchema());
  }

}
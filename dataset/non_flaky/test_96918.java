class DummyClass_96918 {
  @Test
  public void testSnappyCodecUsingAvroCodec() {
    CodecFactory avroSnappyCodec = CodecFactory.fromString("snappy");

    JobConf job = new JobConf();
    job.set("mapred.output.compress", "true");
    job.set(AvroJob.OUTPUT_CODEC, "snappy");
    CodecFactory factory = AvroOutputFormat.getCodecFactory(job);
    assertNotNull(factory);
    assertEquals(factory.getClass(), avroSnappyCodec.getClass());
  }

}
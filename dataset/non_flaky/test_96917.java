class DummyClass_96917 {
  @Test
  public void testSnappyCodecUsingHadoopClass() {
    CodecFactory avroSnappyCodec = CodecFactory.fromString("snappy");

    JobConf job = new JobConf();
    job.set("mapred.output.compress", "true");
    job.set("mapred.output.compression.codec", "org.apache.hadoop.io.compress.SnappyCodec");
    CodecFactory factory = AvroOutputFormat.getCodecFactory(job);
    assertNotNull(factory);
    assertEquals(factory.getClass(), avroSnappyCodec.getClass());
  }

}
class DummyClass_96913 {
  @Test
  public void testBZip2CodecUsingHadoopClass() {
    CodecFactory avroBZip2Codec = CodecFactory.fromString("bzip2");

    JobConf job = new JobConf();
    job.set("mapred.output.compress", "true");
    job.set("mapred.output.compression.codec", "org.apache.hadoop.io.compress.BZip2Codec");
    CodecFactory factory = AvroOutputFormat.getCodecFactory(job);
    assertNotNull(factory);
    assertEquals(factory.getClass(), avroBZip2Codec.getClass());
  }

}
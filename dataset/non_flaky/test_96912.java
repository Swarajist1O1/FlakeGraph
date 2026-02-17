class DummyClass_96912 {
  @Test
  public void testNoCodec() {
    JobConf job = new JobConf();
    assertNull(AvroOutputFormat.getCodecFactory(job));

    job = new JobConf();
    job.set("mapred.output.compress", "false");
    job.set("mapred.output.compression.codec", "org.apache.hadoop.io.compress.BZip2Codec");
    assertNull(AvroOutputFormat.getCodecFactory(job));

    job = new JobConf();
    job.set("mapred.output.compress", "false");
    job.set(AvroJob.OUTPUT_CODEC, "bzip2");
    assertNull(AvroOutputFormat.getCodecFactory(job));
  }

}
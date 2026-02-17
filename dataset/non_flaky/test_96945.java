class DummyClass_96945 {
  @Test
  public void testHadoopCodecFactorySnappy(){
    CodecFactory hadoopSnappyCodec = HadoopCodecFactory.fromHadoopString("org.apache.hadoop.io.compress.SnappyCodec");
    CodecFactory avroSnappyCodec = CodecFactory.fromString("snappy");
    assertTrue(hadoopSnappyCodec.getClass().equals(avroSnappyCodec.getClass()));
  }

}
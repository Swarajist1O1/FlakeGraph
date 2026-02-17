class DummyClass_96948 {
  @Test
  public void testHadoopCodecFactoryFail(){
    CodecFactory hadoopSnappyCodec = HadoopCodecFactory.fromHadoopString("org.apache.hadoop.io.compress.FooCodec");
    assertTrue(hadoopSnappyCodec == null);
  }

}
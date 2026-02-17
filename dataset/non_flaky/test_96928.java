class DummyClass_96928 {
  @Test
  public void testReadSequenceFile() throws Exception {
    checkFile(new SequenceFileReader<>(file()));
  }

}
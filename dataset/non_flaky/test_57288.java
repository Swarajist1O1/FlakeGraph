class DummyClass_57288 {
  @Test
  public void testIntegerCodec() throws IOException {
    Integer i = 1000;
    Codec<Integer> codec = new IntegerCodec();
    byte[] persistedFormat = codec.toPersistedFormat(i);
    Assert.assertTrue(persistedFormat != null);
    Integer fromPersistedFormat =
        codec.fromPersistedFormat(persistedFormat);
    Assert.assertEquals(i, fromPersistedFormat);
  }

}
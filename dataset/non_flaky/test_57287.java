class DummyClass_57287 {
  @Test
  public void testContainerKeyPrefixCodec() throws IOException {
    ContainerKeyPrefix containerKeyPrefix = new ContainerKeyPrefix(
        System.currentTimeMillis(), "TestKeyPrefix", 0);

    Codec<ContainerKeyPrefix> codec = new ContainerKeyPrefixCodec();
    byte[] persistedFormat = codec.toPersistedFormat(containerKeyPrefix);
    Assert.assertTrue(persistedFormat != null);
    ContainerKeyPrefix fromPersistedFormat =
        codec.fromPersistedFormat(persistedFormat);
    Assert.assertEquals(containerKeyPrefix, fromPersistedFormat);
  }

}
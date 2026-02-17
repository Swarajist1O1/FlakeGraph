class DummyClass_57217 {
  @Test
  public void testGetKeyType() throws IOException {
    OzoneConfiguration configuration = createNewTestPath();
    OmMetadataManagerImpl metaMgr = new OmMetadataManagerImpl(configuration);

    assertEquals(String.class, omdbDefinition.getKeyType(
        metaMgr.getKeyTable(getBucketLayout()).getName()).get());
    assertEquals(OzoneTokenIdentifier.class, omdbDefinition.getKeyType(
        metaMgr.getDelegationTokenTable().getName()).get());
  }

}
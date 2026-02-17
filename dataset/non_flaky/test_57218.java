class DummyClass_57218 {
  @Test
  public void testGetValueType() throws IOException {
    OzoneConfiguration configuration = createNewTestPath();
    OmMetadataManagerImpl metaMgr = new OmMetadataManagerImpl(configuration);

    assertEquals(OmKeyInfo.class, omdbDefinition.getValueType(
        metaMgr.getKeyTable(getBucketLayout()).getName()).get());
    assertEquals(OmVolumeArgs.class, omdbDefinition.getValueType(
        metaMgr.getVolumeTable().getName()).get());
    assertEquals(OmBucketInfo.class, omdbDefinition.getValueType(
        metaMgr.getBucketTable().getName()).get());
  }

}
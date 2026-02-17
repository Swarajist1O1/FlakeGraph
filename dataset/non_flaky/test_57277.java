class DummyClass_57277 {
  @Test
  public void testGetKeysForContainer() {
    Response response = containerEndpoint.getKeysForContainer(1L, -1, "");

    KeysResponse data = (KeysResponse) response.getEntity();
    Collection<KeyMetadata> keyMetadataList = data.getKeys();

    assertEquals(3, data.getTotalCount());
    assertEquals(2, keyMetadataList.size());

    Iterator<KeyMetadata> iterator = keyMetadataList.iterator();

    KeyMetadata keyMetadata = iterator.next();
    assertEquals("key_one", keyMetadata.getKey());
    assertEquals(1, keyMetadata.getVersions().size());
    assertEquals(1, keyMetadata.getBlockIds().size());
    Map<Long, List<KeyMetadata.ContainerBlockMetadata>> blockIds =
        keyMetadata.getBlockIds();
    assertEquals(101, blockIds.get(0L).iterator().next().getLocalID());

    keyMetadata = iterator.next();
    assertEquals("key_two", keyMetadata.getKey());
    assertEquals(2, keyMetadata.getVersions().size());
    assertTrue(keyMetadata.getVersions().contains(0L) && keyMetadata
        .getVersions().contains(1L));
    assertEquals(2, keyMetadata.getBlockIds().size());
    blockIds = keyMetadata.getBlockIds();
    assertEquals(103, blockIds.get(0L).iterator().next().getLocalID());
    assertEquals(104, blockIds.get(1L).iterator().next().getLocalID());

    response = containerEndpoint.getKeysForContainer(3L, -1, "");
    data = (KeysResponse) response.getEntity();
    keyMetadataList = data.getKeys();
    assertTrue(keyMetadataList.isEmpty());
    assertEquals(0, data.getTotalCount());

    // test if limit works as expected
    response = containerEndpoint.getKeysForContainer(1L, 1, "");
    data = (KeysResponse) response.getEntity();
    keyMetadataList = data.getKeys();
    assertEquals(1, keyMetadataList.size());
    assertEquals(3, data.getTotalCount());
  }

}
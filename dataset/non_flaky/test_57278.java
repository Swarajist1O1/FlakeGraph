class DummyClass_57278 {
  @Test
  public void testGetKeysForContainerWithPrevKey() {
    // test if prev-key param works as expected
    Response response = containerEndpoint.getKeysForContainer(
        1L, -1, "/sampleVol/bucketOne/key_one");

    KeysResponse data =
        (KeysResponse) response.getEntity();

    assertEquals(3, data.getTotalCount());

    Collection<KeyMetadata> keyMetadataList = data.getKeys();
    assertEquals(1, keyMetadataList.size());

    Iterator<KeyMetadata> iterator = keyMetadataList.iterator();
    KeyMetadata keyMetadata = iterator.next();

    assertEquals("key_two", keyMetadata.getKey());
    assertEquals(2, keyMetadata.getVersions().size());
    assertEquals(2, keyMetadata.getBlockIds().size());

    response = containerEndpoint.getKeysForContainer(
        1L, -1, StringUtils.EMPTY);
    data = (KeysResponse) response.getEntity();
    keyMetadataList = data.getKeys();

    assertEquals(3, data.getTotalCount());
    assertEquals(2, keyMetadataList.size());
    iterator = keyMetadataList.iterator();
    keyMetadata = iterator.next();
    assertEquals("key_one", keyMetadata.getKey());

    // test for negative cases
    response = containerEndpoint.getKeysForContainer(
        1L, -1, "/sampleVol/bucketOne/invalid_key");
    data = (KeysResponse) response.getEntity();
    keyMetadataList = data.getKeys();
    assertEquals(3, data.getTotalCount());
    assertEquals(0, keyMetadataList.size());

    response = containerEndpoint.getKeysForContainer(
        5L, -1, "");
    data = (KeysResponse) response.getEntity();
    keyMetadataList = data.getKeys();
    assertEquals(0, keyMetadataList.size());
    assertEquals(0, data.getTotalCount());
  }

}
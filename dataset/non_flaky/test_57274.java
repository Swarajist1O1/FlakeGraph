class DummyClass_57274 {
  @Test
  public void testGetFileCounts() throws Exception {
    OmKeyInfo omKeyInfo1 = mock(OmKeyInfo.class);
    given(omKeyInfo1.getKeyName()).willReturn("key1");
    given(omKeyInfo1.getVolumeName()).willReturn("vol1");
    given(omKeyInfo1.getBucketName()).willReturn("bucket1");
    given(omKeyInfo1.getDataSize()).willReturn(1000L);

    OmKeyInfo omKeyInfo2 = mock(OmKeyInfo.class);
    given(omKeyInfo2.getKeyName()).willReturn("key2");
    given(omKeyInfo2.getVolumeName()).willReturn("vol1");
    given(omKeyInfo2.getBucketName()).willReturn("bucket1");
    given(omKeyInfo2.getDataSize()).willReturn(100000L);

    OmKeyInfo omKeyInfo3 = mock(OmKeyInfo.class);
    given(omKeyInfo3.getKeyName()).willReturn("key1");
    given(omKeyInfo3.getVolumeName()).willReturn("vol2");
    given(omKeyInfo3.getBucketName()).willReturn("bucket1");
    given(omKeyInfo3.getDataSize()).willReturn(1000L);

    OMMetadataManager omMetadataManager = mock(OmMetadataManagerImpl.class);
    TypedTable<String, OmKeyInfo> keyTable = mock(TypedTable.class);

    TypedTable.TypedTableIterator mockKeyIter = mock(TypedTable
        .TypedTableIterator.class);
    TypedTable.TypedKeyValue mockKeyValue = mock(
        TypedTable.TypedKeyValue.class);

    when(keyTable.iterator()).thenReturn(mockKeyIter);
    when(omMetadataManager.getKeyTable(getBucketLayout())).thenReturn(keyTable);
    when(mockKeyIter.hasNext())
        .thenReturn(true)
        .thenReturn(true)
        .thenReturn(true)
        .thenReturn(false);
    when(mockKeyIter.next()).thenReturn(mockKeyValue);
    when(mockKeyValue.getValue())
        .thenReturn(omKeyInfo1)
        .thenReturn(omKeyInfo2)
        .thenReturn(omKeyInfo3);

    Pair<String, Boolean> result =
        fileSizeCountTask.reprocess(omMetadataManager);
    assertTrue(result.getRight());

    assertEquals(3, fileCountBySizeDao.count());
    Response response = utilizationEndpoint.getFileCounts(null, null, 0);
    List<FileCountBySize> resultSet =
        (List<FileCountBySize>) response.getEntity();
    assertEquals(3, resultSet.size());
    assertTrue(resultSet.stream().anyMatch(o -> o.getVolume().equals("vol1") &&
        o.getBucket().equals("bucket1") && o.getFileSize() == 1024L &&
        o.getCount() == 1L));
    assertTrue(resultSet.stream().anyMatch(o -> o.getVolume().equals("vol1") &&
        o.getBucket().equals("bucket1") && o.getFileSize() == 131072 &&
        o.getCount() == 1L));
    assertTrue(resultSet.stream().anyMatch(o -> o.getVolume().equals("vol2") &&
        o.getBucket().equals("bucket1") && o.getFileSize() == 1024L &&
        o.getCount() == 1L));

    // Test for "volume" query param
    response = utilizationEndpoint.getFileCounts("vol1", null, 0);
    resultSet = (List<FileCountBySize>) response.getEntity();
    assertEquals(2, resultSet.size());
    assertTrue(resultSet.stream().allMatch(o -> o.getVolume().equals("vol1")));

    // Test for non-existent volume
    response = utilizationEndpoint.getFileCounts("vol", null, 0);
    resultSet = (List<FileCountBySize>) response.getEntity();
    assertEquals(0, resultSet.size());

    // Test for "volume" + "bucket" query param
    response = utilizationEndpoint.getFileCounts("vol1", "bucket1", 0);
    resultSet = (List<FileCountBySize>) response.getEntity();
    assertEquals(2, resultSet.size());
    assertTrue(resultSet.stream().allMatch(o -> o.getVolume().equals("vol1") &&
        o.getBucket().equals("bucket1")));

    // Test for non-existent bucket
    response = utilizationEndpoint.getFileCounts("vol1", "bucket", 0);
    resultSet = (List<FileCountBySize>) response.getEntity();
    assertEquals(0, resultSet.size());

    // Test for "volume" + "bucket" + "fileSize" query params
    response = utilizationEndpoint.getFileCounts("vol1", "bucket1", 131072);
    resultSet = (List<FileCountBySize>) response.getEntity();
    assertEquals(1, resultSet.size());
    FileCountBySize o = resultSet.get(0);
    assertTrue(o.getVolume().equals("vol1") && o.getBucket().equals(
        "bucket1") && o.getFileSize() == 131072);

    // Test for non-existent fileSize
    response = utilizationEndpoint.getFileCounts("vol1", "bucket1", 1310725);
    resultSet = (List<FileCountBySize>) response.getEntity();
    assertEquals(0, resultSet.size());
  }

}
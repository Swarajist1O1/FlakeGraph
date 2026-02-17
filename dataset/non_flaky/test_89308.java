class DummyClass_89308 {
  @Test
  public void testGetSystemStreamMetaDataWithNoTopic() {
    Map<String, SystemStreamMetadata> metadataMap = kafkaSystemAdmin.getSystemStreamMetadata(Collections.emptySet());
    assertEquals("empty metadata for no topic", metadataMap.size(), 0);
  }

}
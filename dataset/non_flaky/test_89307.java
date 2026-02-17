class DummyClass_89307 {
  @Test
  public void testGetSystemStreamMetaDataWithInvalidTopic() {
    Map<String, SystemStreamMetadata> metadataMap =
        kafkaSystemAdmin.getSystemStreamMetadata(ImmutableSet.of(INVALID_TOPIC));
    assertEquals("empty metadata for invalid topic", metadataMap.size(), 0);
  }

}
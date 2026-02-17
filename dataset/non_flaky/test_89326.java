class DummyClass_89326 {
  @Test
  public void testGetOffsetsAfter() {
    SystemStreamPartition ssp1 = new SystemStreamPartition(SYSTEM, TOPIC, new Partition(0));
    SystemStreamPartition ssp2 = new SystemStreamPartition(SYSTEM, TOPIC, new Partition(1));
    Map<SystemStreamPartition, String> offsets = new HashMap<>();
    offsets.put(ssp1, "1");
    offsets.put(ssp2, "2");

    offsets = systemAdmin().getOffsetsAfter(offsets);

    Assert.assertEquals("2", offsets.get(ssp1));
    Assert.assertEquals("3", offsets.get(ssp2));
  }

}
class DummyClass_96911 {
  @Test
  public void testSetSyncInterval() {
    JobConf jobConf = new JobConf();
    int newSyncInterval = 100000;
    AvroOutputFormat.setSyncInterval(jobConf, newSyncInterval);

    assertEquals(newSyncInterval, jobConf.getInt(
            AvroOutputFormat.SYNC_INTERVAL_KEY, -1));
  }

}
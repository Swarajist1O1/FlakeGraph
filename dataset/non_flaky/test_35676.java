class DummyClass_35676 {
  @Test
  public void testLogBufferCleanerService() throws Exception {
    String absolutePath = TMP_FOLDER.newFolder().getAbsolutePath();

    MockCheckpointManager checkpointManager = new MockCheckpointManager();
    // update checkpoints
    checkpointManager.saveCheckpoints(ImmutableMap.of(0, new TestCheckpoint(2L, 0L, 1L)));

    // write directly to log buffer, keep file size 10 bytes so that more files are created
    LogBufferWriter writer = new LogBufferWriter(absolutePath, 10,
                                                 new LogBufferCleaner(ImmutableList.of(checkpointManager),
                                                                      absolutePath, new AtomicBoolean(true)));
    ImmutableList<byte[]> events = getLoggingEvents();
    List<byte[]> subset = new ArrayList<>();
    subset.add(events.get(0));
    subset.add(events.get(1));
    subset.add(events.get(2));
    writer.write(subset.iterator()).iterator();

    // should delete file 0 an1
    File file0 = new File(absolutePath, "0.buff");
    File file1 = new File(absolutePath, "1.buff");
    Tasks.waitFor(true, () -> !file0.exists() && !file1.exists(), 120, TimeUnit.SECONDS, 100, TimeUnit.MILLISECONDS);

    // update checkpoints
    checkpointManager.saveCheckpoints(ImmutableMap.of(0, new TestCheckpoint(5L, 0L, 1L)));

    subset.add(events.get(3));
    subset.add(events.get(4));
    subset.add(events.get(5));
    writer.write(subset.iterator()).iterator();
    writer.close();

    // should delete file 2, 3 and 4
    File file2 = new File(absolutePath, "2.buff");
    File file3 = new File(absolutePath, "3.buff");
    File file4 = new File(absolutePath, "4.buff");
    Tasks.waitFor(true, () -> !file2.exists() && !file3.exists() && !file4.exists(), 120, TimeUnit.SECONDS,
                  100, TimeUnit.MILLISECONDS);

  }

}
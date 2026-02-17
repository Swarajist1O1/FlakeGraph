class DummyClass_176875 {
  @Test
  public void testListFiles() throws IOException {
    Path testDir = createTestDirs();
    List<Path> files = IOUtils.listFiles(testDir, "*");
    assertEquals(2, files.size());
    assertTrue(files.contains(testDir.resolve("subFile1")));
    assertFalse(files.contains(testDir.resolve(".hidden")));
    assertTrue(files.contains(testDir.resolve("subDir1")));
  }

}
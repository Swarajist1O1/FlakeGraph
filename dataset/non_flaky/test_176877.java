class DummyClass_176877 {
  @Test
  public void testListSubdirs() throws IOException {
    Path testDir = createTestDirs();
    List<Path> files = IOUtils.listFiles(testDir, "*/*");
    assertEquals(2, files.size());
    assertTrue(files.contains(testDir.resolve("subDir1").resolve("subFile2")));
    assertTrue(files.contains(testDir.resolve("subDir1").resolve("subDir2")));
  }

}
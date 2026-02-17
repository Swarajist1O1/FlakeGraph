class DummyClass_176878 {
  @Test
  public void testListSubdirs2() throws IOException {
    Path testDir = createTestDirs();
    List<Path> files = IOUtils.listFiles(testDir, "*/subFile*");
    assertEquals(1, files.size());
    assertTrue(files.contains(testDir.resolve("subDir1").resolve("subFile2")));
  }

}
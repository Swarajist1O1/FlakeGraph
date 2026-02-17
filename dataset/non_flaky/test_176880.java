class DummyClass_176880 {
  @Test
  public void testReadLines() throws IOException {
    Path tempDir = getTempDir();
    Path textFile = tempDir.resolve("file.txt");
    Files.write(textFile, Arrays.asList("foo", "bar", "baz"), StandardCharsets.UTF_8);
    Iterator<String> it = IOUtils.readLines(textFile).iterator();
    assertTrue(it.hasNext());
    assertEquals("foo", it.next());
    assertTrue(it.hasNext());
    assertEquals("bar", it.next());
    assertTrue(it.hasNext());
    assertEquals("baz", it.next());
    assertFalse(it.hasNext());
  }

}
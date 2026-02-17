class DummyClass_98298 {
  @Test
  public void testFileWithEmptyDirectory() throws Exception {
    Path tempDir = Files.createTempDirectory("dockerDirectoryEmptySubdirectory");
    tempDir.toFile().deleteOnExit();
    assertThat(new File(tempDir.toFile(), "emptySubDir").mkdir(), is(true));

    try (CompressedDirectory dir = CompressedDirectory.create(tempDir);
         BufferedInputStream fileIn = new BufferedInputStream(Files.newInputStream(dir.file()));
         GzipCompressorInputStream gzipIn = new GzipCompressorInputStream(fileIn);
         TarArchiveInputStream tarIn = new TarArchiveInputStream(gzipIn)) {

      final List<String> names = new ArrayList<>();
      TarArchiveEntry entry;
      while ((entry = tarIn.getNextTarEntry()) != null) {
        final String name = entry.getName();
        names.add(name);
      }
      assertThat(names, contains("emptySubDir/"));
    }
  }

}
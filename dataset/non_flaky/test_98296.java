class DummyClass_98296 {
  @Test
  public void testFile() throws Exception {
    // note: Paths.get(someURL.toUri()) is the platform-neutral way to convert a URL to a Path
    final URL dockerDirectory = Resources.getResource("dockerDirectory");
    try (CompressedDirectory dir = CompressedDirectory.create(Paths.get(dockerDirectory.toURI()));
         BufferedInputStream fileIn = new BufferedInputStream(Files.newInputStream(dir.file()));
         GzipCompressorInputStream gzipIn = new GzipCompressorInputStream(fileIn);
         TarArchiveInputStream tarIn = new TarArchiveInputStream(gzipIn)) {

      final List<String> names = new ArrayList<>();
      TarArchiveEntry entry;
      while ((entry = tarIn.getNextTarEntry()) != null) {
        final String name = entry.getName();
        names.add(name);
      }
      assertThat(names,
                 containsInAnyOrder("Dockerfile", "bin/", "bin/date.sh",
                                    "innerDir/", "innerDir/innerDockerfile"));
    }
  }

}
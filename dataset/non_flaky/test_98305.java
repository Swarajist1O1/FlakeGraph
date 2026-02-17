class DummyClass_98305 {
  @Test
  public void testMatchFilepath() {
    if (exception != null) {
      expectedException.expect(exception);
    }

    final Path path = fs.getPath(pathString);
    final boolean result = CompressedDirectory.goPathMatcher(fs, pattern).matches(path);

    final String description;
    if (matched) {
      description = MessageFormat.format("the pattern {0} to match {1}", pattern, pathString);
    } else {
      description = MessageFormat.format("the pattern {0} not to match {1}", pattern, pathString);
    }

    assertThat(result, describedAs(description, is(matched)));
  }

}
class DummyClass_122597 {
        @Test
        public void filename_filters() {
            Path path = Paths.get("/my/fake/path/some-12352-file.json");
            FileFinder.FileAttributes fileAttributes = new FileFinder.FileAttributes(path, attributes);

            assertTrue(FileFinder.nameStartsWith("some-").test(fileAttributes));
            assertFalse(FileFinder.nameStartsWith("som-").test(fileAttributes));

            assertTrue(FileFinder.nameEndsWith(".json").test(fileAttributes));
            assertFalse(FileFinder.nameEndsWith("file").test(fileAttributes));

            assertTrue(FileFinder.nameMatches(Pattern.compile("some-[0-9]+-file.json")).test(fileAttributes));
            assertTrue(FileFinder.nameMatches(Pattern.compile("^some-[0-9]+-file.json$")).test(fileAttributes));
            assertFalse(FileFinder.nameMatches(Pattern.compile("some-[0-9]-file.json")).test(fileAttributes));
        }

}
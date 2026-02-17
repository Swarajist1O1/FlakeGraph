class DummyClass_122595 {
        @Test
        public void age_filter_test() {
            Path path = Paths.get("/my/fake/path");
            when(attributes.lastModifiedTime()).thenReturn(FileTime.from(Instant.now().minus(Duration.ofHours(1))));
            FileFinder.FileAttributes fileAttributes = new FileFinder.FileAttributes(path, attributes);

            assertFalse(FileFinder.olderThan(Duration.ofMinutes(61)).test(fileAttributes));
            assertTrue(FileFinder.olderThan(Duration.ofMinutes(59)).test(fileAttributes));

            assertTrue(FileFinder.youngerThan(Duration.ofMinutes(61)).test(fileAttributes));
            assertFalse(FileFinder.youngerThan(Duration.ofMinutes(59)).test(fileAttributes));
        }

}
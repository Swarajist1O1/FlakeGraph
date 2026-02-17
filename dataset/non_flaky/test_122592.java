class DummyClass_122592 {
        @Test
        public void directory_with_filter() {
            assertFileHelper(FileFinder.directories(testRoot())
                            .match(FileFinder.nameStartsWith("subdir"))
                            .maxDepth(2),

                    of("test/subdir-1", "test/subdir-2"),
                    of("file-1.json", "test.json", "test.txt", "test", "test/file.txt", "test/data.json"));
        }

}
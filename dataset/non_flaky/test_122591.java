class DummyClass_122591 {
        @Test
        public void all_files_limited_depth() {
            assertFileHelper(FileFinder.files(testRoot())
                            .maxDepth(2),

                    of("test.txt", "file-1.json", "test.json", "test/file.txt", "test/data.json"),
                    of("test", "test/subdir-1", "test/subdir-1/test", "test/subdir-2"));
        }

}
class DummyClass_122585 {
        @Test
        public void all_files_non_recursive() {
            assertFileHelper(FileFinder.files(testRoot())
                            .maxDepth(1),

                    of("file-1.json", "test.json", "test.txt"),
                    of("test", "test/file.txt", "test/data.json", "test/subdir-1", "test/subdir-1/test", "test/subdir-2"));
        }

}
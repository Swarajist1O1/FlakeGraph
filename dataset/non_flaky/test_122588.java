class DummyClass_122588 {
        @Test
        public void all_files_recursive_with_prune_absolute() {
            assertFileHelper(FileFinder.files(testRoot()).prune(testRoot().resolve("test/subdir-1")),

                    of("file-1.json", "test.json", "test.txt", "test/file.txt", "test/data.json"),
                    of("test", "test/subdir-1", "test/subdir-1/test", "test/subdir-2"));
        }

}
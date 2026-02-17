class DummyClass_122587 {
        @Test
        public void all_files_recursive_with_prune_relative() {
            assertFileHelper(FileFinder.files(testRoot()).prune(fileSystem.getPath("test")),

                    of("file-1.json", "test.json", "test.txt"),
                    of("test", "test/file.txt", "test/data.json", "test/subdir-1", "test/subdir-1/test", "test/subdir-2"));
        }

}
class DummyClass_122586 {
        @Test
        public void all_files_recursive() {
            assertFileHelper(FileFinder.files(testRoot()),

                    of("file-1.json", "test.json", "test.txt", "test/file.txt", "test/data.json", "test/subdir-1/test"),
                    of("test", "test/subdir-1", "test/subdir-2"));
        }

}
class DummyClass_122590 {
        @Test
        public void with_file_filter_recursive() {
            assertFileHelper(FileFinder.files(testRoot())
                            .match(FileFinder.nameEndsWith(".json")),

                    of("file-1.json", "test.json", "test/data.json"),
                    of("test.txt", "test", "test/file.txt", "test/subdir-1", "test/subdir-1/test", "test/subdir-2"));
        }

}
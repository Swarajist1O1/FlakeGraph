class DummyClass_122593 {
        @Test
        public void match_file_and_directory_with_same_name() {
            assertFileHelper(FileFinder.from(testRoot())
                            .match(FileFinder.nameEndsWith("test")),

                    of("test", "test/subdir-1/test"),
                    of("file-1.json", "test.json", "test.txt"));
        }

}
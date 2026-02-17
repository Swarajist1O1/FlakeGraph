class DummyClass_122594 {
        @Test
        public void all_contents() {
            assertFileHelper(FileFinder.from(testRoot())
                            .maxDepth(1),

                    of("file-1.json", "test.json", "test.txt", "test"),
                    of());

            assertTrue(Files.exists(testRoot()));
        }

}
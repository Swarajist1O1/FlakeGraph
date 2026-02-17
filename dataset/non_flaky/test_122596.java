class DummyClass_122596 {
        @Test
        public void size_filters() {
            Path path = Paths.get("/my/fake/path");
            when(attributes.size()).thenReturn(100L);
            FileFinder.FileAttributes fileAttributes = new FileFinder.FileAttributes(path, attributes);

            assertFalse(FileFinder.largerThan(101).test(fileAttributes));
            assertTrue(FileFinder.largerThan(99).test(fileAttributes));

            assertTrue(FileFinder.smallerThan(101).test(fileAttributes));
            assertFalse(FileFinder.smallerThan(99).test(fileAttributes));
        }

}
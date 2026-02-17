class DummyClass_122589 {
        @Test(expected = IllegalArgumentException.class)
        public void throws_if_prune_path_not_under_base_path() {
            FileFinder.files(Paths.get("/some/path")).prune(Paths.get("/other/path"));
        }

}
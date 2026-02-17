class DummyClass_77558 {
    @Test
        public InputStream open(String path) throws IOException {
            openCalled = true;
            return super.open(path);
        }

}
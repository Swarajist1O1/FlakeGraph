class DummyClass_98576 {
    @Test
    public void test_clipScale_url() throws Throwable {
        File file = Files.findFile(getClass().getPackage().getName().replace('.', '/')
                                    + "/snapshot.jpg");
        Images.clipScale(file.toURI().toURL(), File.createTempFile("abc", "jpg"), 256, 256);
    }

}
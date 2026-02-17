class DummyClass_98575 {
    @Test
    public void test_c() throws Throwable {
        File file = Files.findFile(getClass().getPackage().getName().replace('.', '/')
                                    + "/snapshot.jpg");
        //System.out.println(file.length());
        Images.clipScale(file, File.createTempFile("abc", "jpg"), 256, 256);
    }

}
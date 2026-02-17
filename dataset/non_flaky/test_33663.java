class DummyClass_33663 {
    @Test
    public void test_large() throws Exception {
        SerializeWriter writer = new SerializeWriter();

        for (int i = 0; i < 1024 * 1024; ++i) {
            writer.write(i);
        }

        writer.close();
    }

}
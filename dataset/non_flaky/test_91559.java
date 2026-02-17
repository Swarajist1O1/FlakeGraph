class DummyClass_91559 {
    @Test
    public void testBasic() throws IOException {
        File tempFile = null;
        try (AutoDeleteDirectory autoTempFile = new AutoDeleteDirectory("test", "")) {
            Assert.assertTrue(autoTempFile.getFile().isDirectory());
            Assert.assertEquals(0, autoTempFile.getFile().listFiles().length);
            tempFile = autoTempFile.getFile();
        }
        Assert.assertTrue(!tempFile.exists());
    }

}
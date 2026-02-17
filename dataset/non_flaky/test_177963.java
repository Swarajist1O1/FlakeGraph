class DummyClass_177963 {
    @Test
    public void testReadFile() throws Exception {
        final File file = new File(mContext.getFilesDir(), TEST_FILE);
        final Uri uri = stageFileAndGetUri(file, TEST_DATA);

        assertContentsEquals(TEST_DATA, uri);
    }

}
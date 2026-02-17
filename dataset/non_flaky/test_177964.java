class DummyClass_177964 {
    @Test
    public void testWriteFile() throws Exception {
        final File file = new File(mContext.getFilesDir(), TEST_FILE);
        final Uri uri = stageFileAndGetUri(file, TEST_DATA);

        assertContentsEquals(TEST_DATA, uri);

        final OutputStream out = mResolver.openOutputStream(uri);
        try {
            out.write(TEST_DATA_ALT);
        } finally {
            closeQuietly(out);
        }

        assertContentsEquals(TEST_DATA_ALT, uri);
    }

}
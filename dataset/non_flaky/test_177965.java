class DummyClass_177965 {
    @Test
    public void testWriteMissingFile() throws Exception {
        final File file = new File(mContext.getFilesDir(), TEST_FILE);
        final Uri uri = stageFileAndGetUri(file, null);

        try {
            assertContentsEquals(new byte[0], uri);
            fail("Somehow read missing file?");
        } catch(FileNotFoundException e) {
        }

        final OutputStream out = mResolver.openOutputStream(uri);
        try {
            out.write(TEST_DATA_ALT);
        } finally {
            closeQuietly(out);
        }

        assertContentsEquals(TEST_DATA_ALT, uri);
    }

}
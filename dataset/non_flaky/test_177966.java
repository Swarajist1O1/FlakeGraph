class DummyClass_177966 {
    @Test
    public void testDelete() throws Exception {
        final File file = new File(mContext.getFilesDir(), TEST_FILE);
        final Uri uri = stageFileAndGetUri(file, TEST_DATA);

        assertContentsEquals(TEST_DATA, uri);

        assertEquals(1, mResolver.delete(uri, null, null));
        assertEquals(0, mResolver.delete(uri, null, null));

        try {
            assertContentsEquals(new byte[0], uri);
            fail("Somehow read missing file?");
        } catch(FileNotFoundException e) {
        }
    }

}
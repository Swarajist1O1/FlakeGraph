class DummyClass_177962 {
    @Test
    public void testQueryExtraColumn() throws Exception {
        final File file = new File(mContext.getFilesDir(), TEST_FILE);
        final Uri uri = stageFileAndGetUri(file, TEST_DATA);

        // Verify that extra column doesn't gook things up
        Cursor cursor = mResolver.query(uri, new String[] {
                SIZE, "foobar", DISPLAY_NAME }, null, null, null);
        try {
            assertEquals(1, cursor.getCount());
            cursor.moveToFirst();
            assertEquals(TEST_DATA.length, cursor.getLong(0));
            assertEquals(TEST_FILE, cursor.getString(1));
        } finally {
            cursor.close();
        }
    }

}
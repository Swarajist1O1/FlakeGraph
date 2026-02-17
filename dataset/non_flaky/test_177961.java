class DummyClass_177961 {
    @Test
    public void testQueryProjectionOrder() throws Exception {
        final File file = new File(mContext.getFilesDir(), TEST_FILE);
        final Uri uri = stageFileAndGetUri(file, TEST_DATA);

        // Verify that swapped order works
        Cursor cursor = mResolver.query(uri, new String[] {
                SIZE, DISPLAY_NAME }, null, null, null);
        try {
            assertEquals(1, cursor.getCount());
            cursor.moveToFirst();
            assertEquals(TEST_DATA.length, cursor.getLong(0));
            assertEquals(TEST_FILE, cursor.getString(1));
        } finally {
            cursor.close();
        }

        cursor = mResolver.query(uri, new String[] {
                DISPLAY_NAME, SIZE }, null, null, null);
        try {
            assertEquals(1, cursor.getCount());
            cursor.moveToFirst();
            assertEquals(TEST_FILE, cursor.getString(0));
            assertEquals(TEST_DATA.length, cursor.getLong(1));
        } finally {
            cursor.close();
        }
    }

}
class DummyClass_177960 {
    @Test
    public void testQueryProjectionNull() throws Exception {
        final File file = new File(mContext.getFilesDir(), TEST_FILE);
        final Uri uri = stageFileAndGetUri(file, TEST_DATA);

        // Verify that null brings out default columns
        Cursor cursor = mResolver.query(uri, null, null, null, null);
        try {
            assertEquals(1, cursor.getCount());
            cursor.moveToFirst();
            assertEquals(TEST_FILE, cursor.getString(cursor.getColumnIndex(DISPLAY_NAME)));
            assertEquals(TEST_DATA.length, cursor.getLong(cursor.getColumnIndex(SIZE)));
        } finally {
            cursor.close();
        }
    }

}
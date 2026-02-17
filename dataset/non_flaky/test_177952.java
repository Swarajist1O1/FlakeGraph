class DummyClass_177952 {
    @Test
    public void testStrategyUriSimple() throws Exception {
        final SimplePathStrategy strat = new SimplePathStrategy("authority");
        strat.addRoot("tag", mContext.getFilesDir());

        File file = buildPath(mContext.getFilesDir(), "file.test");
        assertEquals("content://authority/tag/file.test",
                strat.getUriForFile(file).toString());

        file = buildPath(mContext.getFilesDir(), "subdir", "file.test");
        assertEquals("content://authority/tag/subdir/file.test",
                strat.getUriForFile(file).toString());

        file = buildPath(Environment.getExternalStorageDirectory(), "file.test");
        try {
            strat.getUriForFile(file);
            fail("somehow got uri for file outside roots?");
        } catch (IllegalArgumentException e) {
        }
    }

}
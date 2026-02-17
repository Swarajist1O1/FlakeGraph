class DummyClass_177955 {
    @Test
    public void testStrategyFileSimple() throws Exception {
        final SimplePathStrategy strat = new SimplePathStrategy("authority");
        strat.addRoot("tag", mContext.getFilesDir());

        File expectedRoot = mContext.getFilesDir().getCanonicalFile();
        File file = buildPath(expectedRoot, "file.test");
        assertEquals(file.getPath(),
                strat.getFileForUri(Uri.parse("content://authority/tag/file.test")).getPath());

        file = buildPath(expectedRoot, "subdir", "file.test");
        assertEquals(file.getPath(), strat.getFileForUri(
                Uri.parse("content://authority/tag/subdir/file.test")).getPath());
    }

}
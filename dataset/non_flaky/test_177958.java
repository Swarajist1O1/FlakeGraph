class DummyClass_177958 {
    @Test
    public void testStrategyExtraParams() throws Exception {
        final SimplePathStrategy strat = new SimplePathStrategy("authority");
        strat.addRoot("tag", mContext.getFilesDir());

        File expectedRoot = mContext.getFilesDir().getCanonicalFile();
        File file = buildPath(expectedRoot, "file.txt");
        assertEquals(file.getPath(), strat.getFileForUri(
                Uri.parse("content://authority/tag/file.txt?extra=foo")).getPath());
    }

}
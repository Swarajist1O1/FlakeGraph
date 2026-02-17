class DummyClass_177959 {
    @Test
    public void testStrategyExtraSeparators() throws Exception {
        final SimplePathStrategy strat = new SimplePathStrategy("authority");
        strat.addRoot("tag", mContext.getFilesDir());

        // When canonicalized, the path separators are trimmed
        File inFile = new File(mContext.getFilesDir(), "//foo//bar//");
        File expectedRoot = mContext.getFilesDir().getCanonicalFile();
        File outFile = new File(expectedRoot, "/foo/bar");
        final String expected = "content://authority/tag/foo/bar";

        assertEquals(expected,
                strat.getUriForFile(inFile).toString());
        assertEquals(outFile.getPath(),
                strat.getFileForUri(Uri.parse(expected)).getPath());
    }

}
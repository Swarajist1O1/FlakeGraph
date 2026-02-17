class DummyClass_177957 {
    @Test
    public void testStrategyEscaping() throws Exception {
        final SimplePathStrategy strat = new SimplePathStrategy("authority");
        strat.addRoot("t/g", mContext.getFilesDir());

        File expectedRoot = mContext.getFilesDir().getCanonicalFile();
        File file = buildPath(expectedRoot, "lol\"wat?foo&bar", "wat.txt");
        final String expected = "content://authority/t%2Fg/lol%22wat%3Ffoo%26bar/wat.txt";

        assertEquals(expected,
                strat.getUriForFile(file).toString());
        assertEquals(file.getPath(),
                strat.getFileForUri(Uri.parse(expected)).getPath());
    }

}
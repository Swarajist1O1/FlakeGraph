class DummyClass_177954 {
    @Test
    public void testStrategyUriShortestRoot() throws Exception {
        SimplePathStrategy strat = new SimplePathStrategy("authority");
        strat.addRoot("tag1", mContext.getFilesDir());
        strat.addRoot("tag2", new File("/"));

        File file = buildPath(mContext.getFilesDir(), "file.test");
        assertEquals("content://authority/tag1/file.test",
                strat.getUriForFile(file).toString());

        strat = new SimplePathStrategy("authority");
        strat.addRoot("tag1", new File("/"));
        strat.addRoot("tag2", mContext.getFilesDir());

        file = buildPath(mContext.getFilesDir(), "file.test");
        assertEquals("content://authority/tag2/file.test",
                strat.getUriForFile(file).toString());
    }

}
class DummyClass_177953 {
    @Test
    public void testStrategyUriJumpOutside() throws Exception {
        final SimplePathStrategy strat = new SimplePathStrategy("authority");
        strat.addRoot("tag", mContext.getFilesDir());

        File file = buildPath(mContext.getFilesDir(), "..", "file.test");
        try {
            strat.getUriForFile(file);
            fail("file escaped!");
        } catch (IllegalArgumentException e) {
        }
    }

}
class DummyClass_177956 {
    @Test
    public void testStrategyFileJumpOutside() throws Exception {
        final SimplePathStrategy strat = new SimplePathStrategy("authority");
        strat.addRoot("tag", mContext.getFilesDir());

        try {
            strat.getFileForUri(Uri.parse("content://authority/tag/../file.test"));
            fail("file escaped!");
        } catch (SecurityException e) {
        }
    }

}
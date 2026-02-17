class DummyClass_177976 {
    @Test
    public void testMinAlphas() {
        for (TestEntry entry : sEntryList) {
            verifyMinAlpha("Black title", entry.rgb, entry.blackMinAlpha30,
                    ColorUtils.calculateMinimumAlpha(Color.BLACK, entry.rgb, 3.0f));
            verifyMinAlpha("Black body", entry.rgb, entry.blackMinAlpha45,
                    ColorUtils.calculateMinimumAlpha(Color.BLACK, entry.rgb, 4.5f));
            verifyMinAlpha("White title", entry.rgb, entry.whiteMinAlpha30,
                    ColorUtils.calculateMinimumAlpha(Color.WHITE, entry.rgb, 3.0f));
            verifyMinAlpha("White body", entry.rgb, entry.whiteMinAlpha45,
                    ColorUtils.calculateMinimumAlpha(Color.WHITE, entry.rgb, 4.5f));
        }
    }

}
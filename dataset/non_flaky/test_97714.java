class DummyClass_97714 {
    @Test
    public void testDateAsString_forJavaUtilDate() {
        final Settings settings = TestUtils.settings();
        settings.mapDate = DateMapping.asString;
        final String dts = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Dates.class));
        Assert.assertTrue(dts.contains("date: DateAsString;"));
        Assert.assertTrue(dts.contains("dateList: DateAsString[];"));
        Assert.assertTrue(dts.contains("datesMap: { [index: string]: DateAsString[] };"));
        Assert.assertTrue(dts.contains("dates: DateAsString[];"));
        Assert.assertTrue(dts.contains("calendar: DateAsString;"));
        Assert.assertTrue(dts.contains("type DateAsString = string;"));
    }

}
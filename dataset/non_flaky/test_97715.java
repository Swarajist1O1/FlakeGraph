class DummyClass_97715 {
    @Test
    public void testDateAsString_forJava8DateTime() {
        final Settings settings = TestUtils.settings();
        settings.mapDate = DateMapping.asString;
        final String dts = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Java8Dates.class));
        Assert.assertTrue(dts.contains("date: DateAsString;"));
        Assert.assertTrue(dts.contains("dateList: DateAsString[];"));
        Assert.assertTrue(dts.contains("datesMap: { [index: string]: DateAsString[] };"));
        Assert.assertTrue(dts.contains("dates: DateAsString[];"));
        Assert.assertTrue(dts.contains("type DateAsString = string;"));
    }

}
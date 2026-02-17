class DummyClass_97713 {
    @Test
    public void testDateAsNumber_forJavaUtilDate() {
        final Settings settings = TestUtils.settings();
        settings.mapDate = DateMapping.asNumber;
        final String dts = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Dates.class));
        Assert.assertTrue(dts.contains("date: DateAsNumber;"));
        Assert.assertTrue(dts.contains("dateList: DateAsNumber[];"));
        Assert.assertTrue(dts.contains("datesMap: { [index: string]: DateAsNumber[] };"));
        Assert.assertTrue(dts.contains("dates: DateAsNumber[];"));
        Assert.assertTrue(dts.contains("calendar: DateAsNumber;"));
        Assert.assertTrue(dts.contains("type DateAsNumber = number;"));
    }

}
class DummyClass_97712 {
    @Test
    public void testDate_forJavaUtilDate() {
        final Settings settings = TestUtils.settings();
        settings.mapDate = DateMapping.asDate;
        final String dts = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Dates.class));
        Assert.assertTrue(dts.contains("date: Date;"));
        Assert.assertTrue(dts.contains("dateList: Date[];"));
        Assert.assertTrue(dts.contains("datesMap: { [index: string]: Date[] };"));
        Assert.assertTrue(dts.contains("dates: Date[];"));
        Assert.assertTrue(dts.contains("calendar: Date;"));
    }

}
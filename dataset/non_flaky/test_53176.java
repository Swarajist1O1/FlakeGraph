class DummyClass_53176 {
    @Test
    public void testParsing() throws IOException, ParseException {
        Date d = JsonDateDeserializer.getDate(testDateString, new JsonLocation(null, 22, 0, 0));
        Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(testDateString).getTime(), (long) d.getTime());
    }

}
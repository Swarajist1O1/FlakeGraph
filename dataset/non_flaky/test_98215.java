class DummyClass_98215 {
    @Test
    public void testDavComplianceHeader() {

        List<String> l;

        l = FieldValueParser.tokenizeList("1");
        assertArrayEquals(new String[]{"1"}, l.toArray());

        l = FieldValueParser.tokenizeList("1,2,,,,,3,,bind,");
        assertArrayEquals(new String[]{"1","2","3","bind"}, l.toArray());

        l = FieldValueParser.tokenizeList("1,2,<http://example.com/foo,bar>");
        assertArrayEquals(new String[]{"1","2","<http://example.com/foo,bar>"}, l.toArray());
    }

}
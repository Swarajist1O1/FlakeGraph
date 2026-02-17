class DummyClass_97750 {
    @Test
    public void testDate() throws Exception {
        final Settings settings = getTestSettings();
        final Type javaType = A.class.getField("timestamp").getGenericType();
        Assert.assertEquals("DateAsString", TestUtils.compileType(settings, javaType).toString());
    }

}
class DummyClass_97749 {
    @Test
    public void testEnum() throws Exception {
        final Settings settings = getTestSettings();
        final Type javaType = A.class.getField("directions").getGenericType();
        Assert.assertEquals("{ [index: string]: Direction }[]", TestUtils.compileType(settings, javaType).toString());
    }

}
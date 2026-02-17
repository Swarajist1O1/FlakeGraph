class DummyClass_97751 {
    @Test
    public void testExclusion() throws Exception {
        final Settings settings = getTestSettings(Direction.class.getName());
        final Type javaType = A.class.getField("directions").getGenericType();
        Assert.assertEquals("{ [index: string]: any }[]", TestUtils.compileType(settings, javaType).toString());
    }

}
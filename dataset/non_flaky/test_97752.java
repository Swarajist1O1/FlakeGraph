class DummyClass_97752 {
    @Test
    public void testExclusionPattern() throws Exception {
        final Settings settings = TestUtils.settings();
        settings.setExcludeFilter(null, Arrays.asList("**Direction"));
        final Type javaType = A.class.getField("directions").getGenericType();
        Assert.assertEquals("{ [index: string]: any }[]", TestUtils.compileType(settings, javaType).toString());
    }

}
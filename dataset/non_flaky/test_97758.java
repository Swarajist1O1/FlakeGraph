class DummyClass_97758 {
    @Test
    public void testRawTypes() {
        final String output = new TypeScriptGenerator(TestUtils.settings()).generateTypeScript(Input.from(DummyBean.class));
        Assert.assertTrue(output.contains("rawListProperty: any[]"));
        Assert.assertTrue(output.contains("rawMapProperty: { [index: string]: any }"));
    }

}
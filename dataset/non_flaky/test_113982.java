class DummyClass_113982 {
    @Test
    public void testBeanConfToString() throws Exception {
        ConstantConfig constantConfig = new ConstantConfig();

        String actual = constantConfig.beanConfToString(null);
        Assert.assertEquals(null, actual);

        actual = constantConfig.beanConfToString(new BeanConfig(TestBean.class));
        Assert.assertEquals(Container.DEFAULT_NAME, actual);

        String expectedName = "expectedTestBeanName";
        actual = constantConfig.beanConfToString(new BeanConfig(TestBean.class, expectedName));
        Assert.assertEquals(expectedName, actual);
    }

}
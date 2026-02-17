class DummyClass_113986 {
    @Test
    public void testConstructor() throws Exception {
        Class<TestBean> expectedClass = TestBean.class;

        BeanConfig beanConfig = new BeanConfig(expectedClass);

        Assert.assertEquals(expectedClass, beanConfig.getClazz());
        Assert.assertEquals(Container.DEFAULT_NAME, beanConfig.getName());
        Assert.assertEquals(Scope.SINGLETON, beanConfig.getScope());
        Assert.assertEquals(expectedClass, beanConfig.getType());
        Assert.assertFalse(beanConfig.isOnlyStatic());
        Assert.assertFalse(beanConfig.isOptional());
    }

}
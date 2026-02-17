class DummyClass_113987 {
    @Test
    public void testConstructor2() throws Exception {
        Class<TestBean> expectedClass = TestBean.class;
        String expectedName = "expectedBeanName";
        Class<Object> expectedType = Object.class;
        Scope expectedScope = Scope.PROTOTYPE;
        boolean expectedOnlyStatic = true;
        boolean expectedOptional = true;

        BeanConfig beanConfig = new BeanConfig(expectedClass, expectedName, expectedType, expectedScope,
                expectedOnlyStatic, expectedOptional);

        Assert.assertEquals(expectedClass, beanConfig.getClazz());
        Assert.assertEquals(expectedName, beanConfig.getName());
        Assert.assertEquals(expectedScope, beanConfig.getScope());
        Assert.assertEquals(expectedType, beanConfig.getType());
        Assert.assertEquals(expectedOnlyStatic, beanConfig.isOnlyStatic());
        Assert.assertEquals(expectedOptional, beanConfig.isOptional());
    }

}
class DummyClass_170467 {
    @Test
    public void testOpenPojo()
    {
        Validator validator = ValidatorBuilder.create().with(new SetterTester()).with(new GetterTester()).build();
        List<Class> classes = Arrays.asList(MBeanContainer.class, ObjectMBean.class);
        for (Class clazz : classes)
        {
            validator.validate(PojoClassFactory.getPojoClass(clazz));
        }
    }

}
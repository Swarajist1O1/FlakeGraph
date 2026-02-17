class DummyClass_114016 {
    @Test
    public void testGetBean() throws Exception {
        final CdiObjectFactory cdiObjectFactory = new CdiObjectFactory();
        FooConsumer fooConsumer = (FooConsumer) cdiObjectFactory.buildBean(FooConsumer.class.getCanonicalName(), null, false);
        assertNotNull(fooConsumer);
        assertNotNull(fooConsumer.fooService);
    }

}
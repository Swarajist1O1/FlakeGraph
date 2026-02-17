class DummyClass_179459 {
    @Test
    public void test2() throws IntrospectionException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                FieldHelper.getFields(Country.class);
            }

}
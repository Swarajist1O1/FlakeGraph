class DummyClass_26858 {
    @Test
    public void testStoredContext() throws Exception {
        final ServiceName bindingName = ServiceName.JBOSS.append("foo-stored").append("again");
        bindObject(bindingName, new Context() {
            @Override
            public Object lookup(Name name) throws NamingException {
                if ("blah/blah2".equals(name.toString())) {
                    return new Integer(5);
                }

                return null;
            }

}
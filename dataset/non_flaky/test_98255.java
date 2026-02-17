class DummyClass_98255 {
    @Test
    public void listServices() throws InvalidSyntaxException {
        for (ServiceReference<?> reference
                : context.getAllServiceReferences(null, null)) {
            System.out.println(reference);
        }
    }

}
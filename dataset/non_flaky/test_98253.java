class DummyClass_98253 {
    @Test
    public void bundleStates() {
        for (Bundle bundle : context.getBundles()) {
            assertEquals(
                String.format("Bundle %s not active. have a look at the logs", bundle.toString()), 
                Bundle.ACTIVE, bundle.getState());
        }
    }

}
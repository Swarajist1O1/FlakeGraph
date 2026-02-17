class DummyClass_162359 {
    @Test
    public void testConfig() {
        App app = AppConfig.getApp();
        assertEquals("This is a string", app.getParam("stringParam"));
        assertEquals(new Long(42), app.getParam("intParam"));
        
        TrialBean component1 = app.getComponentAs("component1", TrialBean.class);
        assertEquals("name 1", component1.getProp1());
        assertEquals(1, component1.getProplong());
        assertEquals(true, component1.isProp());
        
        TrialBean component2 = app.getComponentAs("component2", TrialBean.class);
        assertEquals("name 2", component2.getProp1());
        assertEquals(component1, component2.getRef());
        assertEquals(false, component2.isProp());
        
        TrialBean component3 = app.getComponentAs("component3", TrialBean.class);
        List<Object> xref = component3.getXref();
        assertNotNull(xref);
        assertEquals(2, xref.size());
        assertEquals(component1, xref.get(0));
        assertEquals(component2, xref.get(1));
    }

}
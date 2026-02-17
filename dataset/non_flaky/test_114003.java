class DummyClass_114003 {
	@Test
    public void getActionMapping() {
        ActionMapping mapping = getActionMapping("/test/testAction.action");
        Assert.assertNotNull(mapping);
        Assert.assertEquals("/test", mapping.getNamespace());
        Assert.assertEquals("testAction", mapping.getName());
    }

}
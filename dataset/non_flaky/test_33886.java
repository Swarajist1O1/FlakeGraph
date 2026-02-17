class DummyClass_33886 {
    @Test
    public void testGetFromServer() throws Exception {
        // using Class message body for single parameter "metaType"
        IBaseMetaType result = requestBody("direct://GET_FROM_SERVER", Meta.class);
        assertNotNull(result, "getFromServer result");
        LOG.debug("getFromServer: " + result);
    }

}
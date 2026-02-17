class DummyClass_35736 {
  @Test
  public void testTypeHandlerRequests() throws Exception {
    Assert.assertEquals("listModules", doRequest("/namespaces/myspace/data/modules", "GET"));
    Assert.assertEquals("post:myModule", doRequest("/namespaces/myspace/data/modules/myModule", "POST"));
    Assert.assertEquals("delete:myModule", doRequest("/namespaces/myspace/data/modules/myModule", "DELETE"));
    Assert.assertEquals("get:myModule", doRequest("/namespaces/myspace/data/modules/myModule", "GET"));
    Assert.assertEquals("listTypes", doRequest("/namespaces/myspace/data/types", "GET"));
    Assert.assertEquals("getType:myType", doRequest("/namespaces/myspace/data/types/myType", "GET"));
  }

}
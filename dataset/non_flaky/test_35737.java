class DummyClass_35737 {
  @Test
  public void testInstanceHandlerRequests() throws Exception {
    Assert.assertEquals("list", doRequest("/namespaces/myspace/data/datasets", "GET"));
    Assert.assertEquals("post:myInstance",
                        doRequest("/namespaces/myspace/data/datasets/myInstance", "POST"));
    Assert.assertEquals("delete:myInstance",
                        doRequest("/namespaces/myspace/data/datasets/myInstance", "DELETE"));
    Assert.assertEquals("get:myInstance",
                        doRequest("/namespaces/myspace/data/datasets/myInstance", "GET"));
  }

}
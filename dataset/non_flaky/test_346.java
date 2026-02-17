class DummyClass_346 {
  @Test
  public void testNumOfChildren() throws Exception {
    assertEquals(cluster.getNumOfLeaves(), dataNodes.length);
  }

}
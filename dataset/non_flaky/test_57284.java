class DummyClass_57284 {
  @Test
  public void testUnhealthyContainersInvalidState() {
    try {
      containerEndpoint.getUnhealthyContainers("invalid", 1000, 1);
      fail("Expected exception to be raised");
    } catch (WebApplicationException e) {
      assertEquals("HTTP 400 Bad Request", e.getMessage());
    }
  }

}
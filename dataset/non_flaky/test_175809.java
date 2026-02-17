class DummyClass_175809 {
  @Test
  public void testInvalidUrlErrorMessage() {
    assertEquals("Invalid URL: http://www.example.com", 
        Messages.getString("invalid.url", "http://www.example.com"));
  }

}
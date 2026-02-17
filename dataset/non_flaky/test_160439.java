class DummyClass_160439 {
  @Test
  public void defaultBlockSelector_shouldThrowBadRequestForBadHexState() {
    assertThrows(BadRequestException.class, () -> factory.defaultStateSelector("0xzz"));
  }

}
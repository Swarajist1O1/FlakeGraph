class DummyClass_176870 {
  @Test
  public void testSkeleton() {
    PMML pmml = PMMLUtils.buildSkeletonPMML();
    assertEquals("Oryx", pmml.getHeader().getApplication().getName());
    assertNotNull(pmml.getHeader().getTimestamp());
  }

}
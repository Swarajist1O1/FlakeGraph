class DummyClass_156100 {
  @Test
  public void testXNodeSet() {
    final String clazz = "org.apache.xpath.objects.XNodeSet";
    final String[] params = { "org.apache.xpath.objects.XObject", "org.apache.xpath.objects.Comparator" };
    runXalanTest(prepareTarget(methodSigFromComponents(clazz, "boolean", "compare", params), clazz));
  }

}
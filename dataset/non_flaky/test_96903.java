class DummyClass_96903 {
  @Test
  public void testResolving() throws ParseException, MalformedURLException, IOException {
    File file = new File(".");
    String currentWorkPath = file.getAbsolutePath();
    String testIdl = currentWorkPath + File.separator + "src" + File.separator + "test"
        + File.separator + "idl" + File.separator + "cycle.avdl";
    Idl compiler = new Idl(new File(testIdl));
    Protocol protocol = compiler.CompilationUnit();
    System.out.println(protocol);
    Assert.assertEquals(5, protocol.getTypes().size());
  }

}
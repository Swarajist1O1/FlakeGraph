class DummyClass_156139 {
  @Test
  public void testLoadingJava11ClassFromCI() {
    G.reset();
    Main.main(new String[] { "-soot-modulepath", "VIRTUAL_FS_FOR_JDK", "-pp", "-src-prec", "only-class",
        "java.lang.invoke.ConstantBootstraps" });

    SootClass klass = Scene.v().getSootClass("java.lang.invoke.ConstantBootstraps");
    assertTrue(klass.getName().equals("java.lang.invoke.ConstantBootstraps"));
    assertTrue(klass.moduleName.equals("java.base"));

  }

}
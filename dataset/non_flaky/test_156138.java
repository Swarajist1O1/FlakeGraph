class DummyClass_156138 {
  @Test
  public void testLoadingJava9ClassFromCI() {
    G.reset();
    Main.main(new String[] { "-soot-modulepath", "VIRTUAL_FS_FOR_JDK", "-pp", "-src-prec", "only-class",
        "java.lang.invoke.VarHandle" });

    SootClass klass = Scene.v().getSootClass("java.lang.invoke.VarHandle");
    assertTrue(klass.getName().equals("java.lang.invoke.VarHandle"));
    assertTrue(klass.moduleName.equals("java.base"));

  }

}
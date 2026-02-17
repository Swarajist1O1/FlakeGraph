class DummyClass_156137 {
  @Test
  public void testLoadingJava9to11Class() {
    G.reset();
    Options.v().set_soot_modulepath("VIRTUAL_FS_FOR_JDK");
    Scene.v().loadBasicClasses();

    SootClass klass1
        = SootModuleResolver.v().resolveClass("java.lang.invoke.VarHandle", SootClass.BODIES, Optional.of("java.base"));

    assertTrue(klass1.getName().equals("java.lang.invoke.VarHandle"));
    assertTrue(klass1.moduleName.equals("java.base"));

    SootClass klass2 = SootModuleResolver.v().resolveClass("java.lang.invoke.ConstantBootstraps", SootClass.BODIES,
        Optional.of("java.base"));

    assertTrue(klass2.getName().equals("java.lang.invoke.ConstantBootstraps"));
    assertTrue(klass2.moduleName.equals("java.base"));

    Scene.v().loadNecessaryClasses();
  }

}
class DummyClass_156172 {
    @Test
    public void ownPackage() {
        G.reset();
        ModuleUtil moduleUtil = ModuleUtil.v();
        ModuleScene moduleScene = ModuleScene.v();

        SootModuleInfo moduleA = new SootModuleInfo(SootModuleInfo.MODULE_INFO, "moduleA");
        moduleA.addExportedPackage("de.upb");
        moduleScene.addClassSilent(moduleA);


        String foundModule = moduleUtil.declaringModule("de.upb", "moduleA");
        Assert.assertEquals("moduleA", foundModule);
    }

}
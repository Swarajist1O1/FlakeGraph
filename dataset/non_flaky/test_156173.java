class DummyClass_156173 {
    @Test
    public void simpleExport() {
        G.reset();
        ModuleScene moduleScene = ModuleScene.v();

        SootModuleInfo moduleA = new SootModuleInfo(SootModuleInfo.MODULE_INFO, "moduleA");
        moduleA.addExportedPackage("de.upb");
        moduleScene.addClassSilent(moduleA);

        SootModuleInfo moduleB = new SootModuleInfo(SootModuleInfo.MODULE_INFO, "moduleB");
        moduleB.getRequiredModules().put(moduleA, Modifier.REQUIRES_STATIC);
        moduleScene.addClassSilent(moduleB);

        ModuleUtil moduleUtil = ModuleUtil.v();
        String foundModule = moduleUtil.declaringModule("de.upb.A", "moduleB");
        Assert.assertEquals("moduleA", foundModule);

    }

}
class DummyClass_156174 {
    @Test
    public void simpleRequiresTransitiveExport() {
        G.reset();
        ModuleScene moduleScene = ModuleScene.v();

        SootModuleInfo moduleA = new SootModuleInfo(SootModuleInfo.MODULE_INFO, "moduleA");
        moduleA.addExportedPackage("de.upb");
        moduleScene.addClassSilent(moduleA);

        SootModuleInfo moduleB = new SootModuleInfo(SootModuleInfo.MODULE_INFO, "moduleB");
        moduleB.getRequiredModules().put(moduleA, Modifier.REQUIRES_TRANSITIVE);
        moduleScene.addClassSilent(moduleB);


        SootModuleInfo moduleC = new SootModuleInfo(SootModuleInfo.MODULE_INFO, "moduleC");
        moduleC.getRequiredModules().put(moduleB, Modifier.REQUIRES_STATIC);
        moduleScene.addClassSilent(moduleC);

        ModuleUtil moduleUtil = ModuleUtil.v();
        String foundModule = moduleUtil.declaringModule("de.upb.A", "moduleC");
        Assert.assertEquals("moduleA", foundModule);

    }

}
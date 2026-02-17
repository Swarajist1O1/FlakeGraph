class DummyClass_156175 {
    @Test
    public void TwoLevelRequiresTransitiveExport() {
        G.reset();
        ModuleScene moduleScene = ModuleScene.v();

        SootModuleInfo moduleA = new SootModuleInfo(SootModuleInfo.MODULE_INFO, "moduleA");
        moduleA.addExportedPackage("de.upb");
        moduleScene.addClassSilent(moduleA);

        SootModuleInfo moduleB = new SootModuleInfo(SootModuleInfo.MODULE_INFO, "moduleB");
        moduleB.getRequiredModules().put(moduleA, Modifier.REQUIRES_TRANSITIVE);
        moduleScene.addClassSilent(moduleB);


        SootModuleInfo moduleC = new SootModuleInfo(SootModuleInfo.MODULE_INFO, "moduleC");
        moduleC.getRequiredModules().put(moduleB, Modifier.REQUIRES_TRANSITIVE);
        moduleScene.addClassSilent(moduleC);


        SootModuleInfo moduleD = new SootModuleInfo(SootModuleInfo.MODULE_INFO, "moduleD");
        moduleD.getRequiredModules().put(moduleC, Modifier.REQUIRES_STATIC);
        moduleScene.addClassSilent(moduleD);

        ModuleUtil moduleUtil = ModuleUtil.v();
        String foundModule = moduleUtil.declaringModule("de.upb.A", "moduleD");
        // output should be D, because module C, does NOT REQUIERS TRANSITIVE module B
        Assert.assertEquals("moduleA", foundModule);

    }

}
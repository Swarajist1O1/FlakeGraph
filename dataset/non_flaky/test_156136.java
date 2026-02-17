class DummyClass_156136 {
    @Test
    public void testDisableUnusedLocalEliminatorInJBPhase() {
        {
            // default UnusedLocalEliminator enabled
            setup();
            Scene.v().loadNecessaryClasses();
            PackManager.v().runBodyPacks();
            SootClass cls = Scene.v().getSootClass("Example");
            SootMethod bar = cls.getMethodByName("bar");
            List<String> actual = bodyAsStrings(bar.getActiveBody());
            List<String> expected = expectedBody("r1 := @this: Example",
                    "i0 := @parameter0: int",
                    "i1 := @parameter1: int",
                    "i2 = i0 * i1",
                    "$r0 = <java.lang.System: java.io.PrintStream out>",
                    "virtualinvoke $r0.<java.io.PrintStream: void println(int)>(i2)",
                    "return");
        }
        {
            //disable UnusedLocalEliminator
            setup();
            Options.v().setPhaseOption("jb.sils", "enabled:false");// this transformer calls a lot of other transformers
            Options.v().setPhaseOption("jb.cp-ule", "enabled:false");
            Scene.v().loadNecessaryClasses();
            PackManager.v().runBodyPacks();
            SootClass cls = Scene.v().getSootClass("Example");
            SootMethod bar = cls.getMethodByName("bar");
            List<String> actual = bodyAsStrings(bar.getActiveBody());
            List<String> expected = expectedBody("r1 := @this: Example",
                    "i0 := @parameter0: int",
                    "i1 := @parameter1: int",
                    "i2 = i0 * i1",
                    "z0 = 0",
                    "$r0 = <java.lang.System: java.io.PrintStream out>",
                    "virtualinvoke $r0.<java.io.PrintStream: void println(int)>(i2)",
                    "return");
        }
    }

}
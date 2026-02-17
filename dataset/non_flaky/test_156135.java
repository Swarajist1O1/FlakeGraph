class DummyClass_156135 {
    @Test
    public void testDisableCopyPropagatorInJBPhase() {
        {
            // default CopyPropagator enabled
            setup();
            Scene.v().loadNecessaryClasses();
            PackManager.v().runBodyPacks();
            SootClass cls = Scene.v().getSootClass("Example");
            SootMethod foo = cls.getMethodByName("foo");
            List<String> actual = bodyAsStrings(foo.getActiveBody());
            List<String> expected = expectedBody("r0 := @this: Example",
                    "virtualinvoke r0.<Example: void bar(int,int)>(0, 2)",
                    "return");
            assertEquals(expected, actual);
        }
        {
            // disable CopyPropagator
            setup();
            Options.v().setPhaseOption("jb.sils", "enabled:false");// this transformer calls a lot of other transformers
            Options.v().setPhaseOption("jb.cp", "enabled:false");
            Scene.v().loadNecessaryClasses();
            PackManager.v().runBodyPacks();
            SootClass cls = Scene.v().getSootClass("Example");
            SootMethod foo = cls.getMethodByName("foo");
            List<String> actual = bodyAsStrings(foo.getActiveBody());
            List<String> expected = expectedBody("r0 := @this: Example",
                    "b0 = 0",
                    "b1 = 2",
                    "virtualinvoke r0.<Example: void bar(int,int)>(b0, b1)",
                    "return");
            assertEquals(expected, actual);
        }
    }

}
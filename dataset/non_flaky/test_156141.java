class DummyClass_156141 {
    @Test
    public void testMethodWithNoInstruction() {
        setup();
        Options.v().set_output_format(Options.output_format_jimple);
        runTest();
        setup();
        Options.v().set_output_format(Options.output_format_grimp);
        runTest();
        setup();
        Options.v().set_output_format(Options.output_format_baf);
        runTest();
        setup();
        Options.v().set_output_format(Options.output_format_dava);
        runTest();
        setup();
        Options.v().set_output_format(Options.output_format_shimp);
        runTest();
        setup();
        Options.v().set_output_format(Options.output_format_class);
        runTest();
    }

}
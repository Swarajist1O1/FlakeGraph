class DummyClass_122551 {
    @Test
    public void return_expectations() {
        assertSystemCtlMethod(sct -> sct.expectEnable(unit), sc -> sc.enable(unit).converge(context));
        assertSystemCtlMethod(sct -> sct.expectDisable(unit), sc -> sc.disable(unit).converge(context));
        assertSystemCtlMethod(sct -> sct.expectStart(unit), sc -> sc.start(unit).converge(context));
        assertSystemCtlMethod(sct -> sct.expectStop(unit), sc -> sc.stop(unit).converge(context));
        assertSystemCtlMethod(sct -> sct.expectServiceExists(unit), sc -> sc.serviceExists(context, unit));
        assertSystemCtlMethod(sct -> sct.expectIsActive(unit), sc -> sc.isActive(context, unit));
    }

}
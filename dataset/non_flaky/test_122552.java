class DummyClass_122552 {
    @Test
    public void void_tests() {
        systemCtl.expectRestart(unit);
        systemCtl.restart(unit).converge(context);
        terminal.verifyAllCommandsExecuted();

        systemCtl.expectDaemonReload();
        systemCtl.daemonReload(context);
        terminal.verifyAllCommandsExecuted();
    }

}
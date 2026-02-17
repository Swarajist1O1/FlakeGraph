class DummyClass_122560 {
    @Test
    public void waitingForTerminationExceptionStillClosesChild() {
        TestChildProcess2 child = new TestChildProcess2(0, "");
        child.throwInWaitForTermination(new NegativeArraySizeException());
        terminal.interceptCommand(commandLine.toString(), command -> child);
        assertFalse(child.closeCalled());
        try {
            commandLine.add("foo").execute();
            fail();
        } catch (NegativeArraySizeException e) {
            // OK
        }

        assertTrue(child.closeCalled());
    }

}
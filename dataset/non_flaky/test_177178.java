class DummyClass_177178 {
    @Test
    public void callbackContextIsFromInvocationTime_root() {
        try (SafeCloseable ignored = serverContext().push()) {
            super.callbackContextIsFromInvocationTime_root();
        }
    }

}
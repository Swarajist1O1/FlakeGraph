class DummyClass_177180 {
    @Test
    public void usesParentFromInvocationTime() {
        try (SafeCloseable ignored = serverContext().push()) {
            super.usesParentFromInvocationTime();
        }
    }

}
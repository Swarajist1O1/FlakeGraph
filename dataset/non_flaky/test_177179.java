class DummyClass_177179 {
    @Test
    public void addsStatusCodeWhenNotOk_async() {
        try (SafeCloseable ignored = serverContext().push()) {
            super.addsStatusCodeWhenNotOk_async();
        }
    }

}
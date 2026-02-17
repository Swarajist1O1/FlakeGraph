class DummyClass_177198 {
    @Test
            public void pop(RequestContext current, @Nullable RequestContext toRestore) {
                popped.set(true);
                super.pop(current, toRestore);
            }

}
class DummyClass_122601 {
    @Test
    public void deleteExisting() {
        assertFalse(deleter.converge(context));
        path.createParents().writeUtf8File("bar");
        assertTrue(deleter.converge(context));
        assertFalse(deleter.converge(context));
    }

}
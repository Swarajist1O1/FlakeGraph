class DummyClass_53164 {
    @Test
    public void testPatch(){
        group.patch(patch);
        assertEquals(patch.getId(), group.getId());
        assertEquals("NewName",group.getDisplayName());
        assertEquals("NewDescription", group.getDescription());
    }

}
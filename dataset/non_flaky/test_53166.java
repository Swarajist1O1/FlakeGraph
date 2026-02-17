class DummyClass_53166 {
    @Test
    public void testPatchDeleteMetaAttributes(){
        assertEquals("description", group.getDescription());
        String[] attributes = new String[]{"description"};
        patch.getMeta().setAttributes(attributes);
        group.patch(patch);
        assertEquals("NewDescription", group.getDescription());

        patch.setDescription(null);
        group.patch(patch);
        assertNull(group.getDescription());
    }

}
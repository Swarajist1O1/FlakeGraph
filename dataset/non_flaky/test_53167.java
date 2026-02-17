class DummyClass_53167 {
    @Test
    public void testDropDisplayName(){
        patch.setDisplayName("NewDisplayName");
        group.setDisplayName("display");
        assertEquals("display", group.getDisplayName());
        String[] attributes = new String[]{"displayname"};
        patch.getMeta().setAttributes(attributes);
        group.patch(patch);
        assertEquals("NewDisplayName", group.getDisplayName());

        patch.setDisplayName(null);
        group.patch(patch);
        assertNull(group.getDisplayName());
    }

}
class DummyClass_53170 {
    @Test
    public void testDropAllMembers(){
        group.setMembers(Arrays.asList(member1, member2, member3));
        assertEquals(3, group.getMembers().size());
        patch.getMeta().setAttributes(new String[] {"members"});
        group.patch(patch);
        assertEquals(0, group.getMembers().size());
    }

}
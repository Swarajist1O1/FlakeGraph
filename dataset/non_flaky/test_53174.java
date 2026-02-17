class DummyClass_53174 {
    @Test
    public void testAddOneMember() {
        patch.setMembers(Arrays.asList(member1));
        group.setMembers(Arrays.asList(member2, member3));
        assertEquals(2, group.getMembers().size());
        group.patch(patch);
        assertEquals(3, group.getMembers().size());

    }

}
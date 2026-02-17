class DummyClass_53173 {
    @Test
    public void testAddAllMembers() {
        patch.setMembers(Arrays.asList(member1, member2, member3));
        group.setMembers(emptyList());
        assertEquals(0, group.getMembers().size());
        group.patch(patch);
        assertEquals(3, group.getMembers().size());

    }

}
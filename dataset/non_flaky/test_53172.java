class DummyClass_53172 {
    @Test
    public void testDropAllMembersUsingOperation() {
        member1.setOperation("delete");
        member2.setOperation("delete");
        member3.setOperation("delete");
        group.setMembers(Arrays.asList(member1, member2, member3));
        patch.setMembers(group.getMembers());
        assertEquals(3, group.getMembers().size());
        group.patch(patch);
        assertEquals(0, group.getMembers().size());

    }

}
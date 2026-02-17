class DummyClass_53171 {
    @Test
    public void testDropOneMembers(){
        group.setMembers(Arrays.asList(member1, member2, member3));
        ScimGroupMember member = new ScimGroupMember(member1.getMemberId());
        member.setOperation("DELETE");
        patch.setMembers(Arrays.asList(
            member
        ));
        group.patch(patch);
        assertEquals(2, group.getMembers().size());
    }

}
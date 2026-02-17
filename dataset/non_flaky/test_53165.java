class DummyClass_53165 {
    @Test
    public void testPatchZoneIdFails(){
        group.setZoneId("uaa");
        patch.setZoneId("zoneid");

        assertTrue(group.getZoneId().equals("uaa"));
        assertTrue(patch.getZoneId().equals("zoneid"));

        group.patch(patch);

        assertTrue(group.getZoneId().equals("uaa"));
        assertTrue(patch.getZoneId().equals("zoneid"));
    }

}
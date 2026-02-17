class DummyClass_53168 {
    @Test(expected = IllegalArgumentException.class)
    public void cant_drop_zone_id() {
        patch.getMeta().setAttributes(new String[] {"zoneID"});
        group.patch(patch);
    }

}
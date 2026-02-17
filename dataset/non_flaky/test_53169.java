class DummyClass_53169 {
    @Test(expected = IllegalArgumentException.class)
    public void cant_drop_id() {
        patch.getMeta().setAttributes(new String[] {"id"});
        group.patch(patch);
    }

}
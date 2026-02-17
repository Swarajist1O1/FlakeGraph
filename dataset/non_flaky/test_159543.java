class DummyClass_159543 {
    @Test
    public void sortMultiFailures() {
        RealmResults<AllTypes> allTypes = realm.where(AllTypes.class).findAll();

        // Zero fields specified.
        try {
            allTypes.sort(new String[]{}, new Sort[]{});
            fail();
        } catch (IllegalArgumentException ignored) {
        }

        // Number of fields and sorting orders don't match.
        try {
            allTypes.sort(new String[]{FIELD_STRING}, ORDER_ASC_ASC);
            fail();
        } catch (IllegalArgumentException ignored) {
        }

        // Null is not allowed.
        try {
            allTypes.sort(null, (Sort[]) null);
            fail();
        } catch (IllegalArgumentException ignored) {
        }
        try {
            allTypes.sort(new String[]{FIELD_STRING}, null);
            fail();
        } catch (IllegalArgumentException ignored) {
        }

        // Non-existing field name.
        try {
            allTypes.sort(new String[]{FIELD_STRING, "dont-exist"}, ORDER_ASC_ASC);
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

}
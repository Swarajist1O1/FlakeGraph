class DummyClass_159547 {
    @Test
    public void realmSortMultiFailures() {
        RealmResults<AllTypes> allTypes = realm.where(AllTypes.class).findAll();

        // Zero fields specified.
        try {
            realm.where(AllTypes.class).findAll().sort(new String[]{}, new Sort[]{});
            fail();
        } catch (IllegalArgumentException ignored) {
        }

        // Number of fields and sorting orders don't match.
        try {
            realm.where(AllTypes.class).findAll().
                    sort(new String[]{FIELD_STRING}, ORDER_ASC_ASC);
            fail();
        } catch (IllegalArgumentException ignored) {
        }

        // Null is not allowed.
        try {
            realm.where(AllTypes.class).findAll().sort(null, (Sort[]) null);
            fail();
        } catch (IllegalArgumentException ignored) {
        }
        try {
            realm.where(AllTypes.class).findAll().sort(new String[]{FIELD_STRING}, null);
            fail();
        } catch (IllegalArgumentException ignored) {
        }

        // Non-existing field name.
        try {
            realm.where(AllTypes.class).findAll().
                    sort(new String[]{FIELD_STRING, "dont-exist"}, ORDER_ASC_ASC);
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

}
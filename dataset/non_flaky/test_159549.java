class DummyClass_159549 {
    @Test
    public void sortingDates() {
        final int TEST_SIZE = 10;

        populateDates(realm, TEST_SIZE);

        RealmResults<AllTypes> objectsAscending = realm.where(AllTypes.class).findAllSorted(AllTypes.FIELD_DATE, Sort.ASCENDING);
        assertEquals(TEST_SIZE, objectsAscending.size());
        int i = 0;
        for (AllTypes allTypes : objectsAscending) {
            assertEquals(new Date(i), allTypes.getColumnDate());
            i++;
        }

        RealmResults<AllTypes> objectsDescending = realm.where(AllTypes.class).findAllSorted(AllTypes.FIELD_DATE, Sort.DESCENDING);
        assertEquals(TEST_SIZE, objectsDescending.size());
        i = TEST_SIZE - 1;
        for (AllTypes allTypes : objectsDescending) {
            assertEquals(new Date(i), allTypes.getColumnDate());
            i--;
        }
    }

}
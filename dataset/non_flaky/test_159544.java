class DummyClass_159544 {
    @Test
    public void sortRealmResultsTwoFields() {
        RealmResults<AllTypes> results1 = realm.where(AllTypes.class).findAll().sort(ORDER_STRING_INT, ORDER_ASC_ASC);
        checkSortTwoFieldsStringAscendingIntAscending(results1);

        RealmResults<AllTypes> results2 = realm.where(AllTypes.class).findAll().sort(ORDER_INT_STRING, ORDER_ASC_ASC);
        checkSortTwoFieldsIntString(results2);

        RealmResults<AllTypes> results3 = realm.where(AllTypes.class).findAll().sort(ORDER_STRING_INT, ORDER_ASC_DES);
        checkSortTwoFieldsStringAscendingIntDescending(results3);

        RealmResults<AllTypes> results4 = realm.where(AllTypes.class).findAll().sort(ORDER_INT_STRING, ORDER_ASC_DES);
        checkSortTwoFieldsIntAscendingStringDescending(results4);
    }

}
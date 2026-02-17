class DummyClass_159579 {
    @Test
    public void testShouldThrowExceptionWhenColumnNameIsTooLong() {

        Table table = new Table();
        try {
            table.addColumn(RealmFieldType.STRING, "THIS STRING HAS 64 CHARACTERS, "
                    + "LONGER THAN THE MAX 63 CHARACTERS");
            fail("Too long name");
        } catch (IllegalArgumentException e) {
        }
    }

}
class DummyClass_159581 {
    @Test
    public void testGenericAddOnTable() {
        for (int i = 0; i < value.size(); i++) {
            for (int j = 0; j < value.size(); j++) {

                Table t = new Table();

                // If the objects matches no exception will be thrown.
                if (value.get(i).getClass().equals(value.get(j).getClass())) {
                    assertTrue(true);

                } else {
                    // Adds column.
                    t.addColumn(TestHelper.getColumnType(value.get(j)), value.get(j).getClass().getSimpleName());
                    // Adds value.
                    try {
                        t.add(value.get(i));
                        fail("No matching type");
                    } catch (IllegalArgumentException e) {
                    }
                }
            }
        }
    }

}
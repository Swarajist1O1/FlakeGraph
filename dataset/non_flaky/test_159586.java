class DummyClass_159586 {
    @Test
    public void findFirst() {
        final int TEST_SIZE = 10;
        Table t = TestHelper.getTableWithAllColumnTypes();
        for (int i = 0; i < TEST_SIZE; i++) {
            t.add(new byte[]{1,2,3}, true, new Date(i), (double)i, (float)i, i, "string " + i);
        }
        t.add(new byte[]{1, 2, 3}, true, new Date(TEST_SIZE), (double) TEST_SIZE, (float) TEST_SIZE, TEST_SIZE, "");

        assertEquals(0, t.findFirstBoolean(1, true));
        for (int i = 0; i < TEST_SIZE; i++) {
            assertEquals(i, t.findFirstDate(2, new Date(i)));
            assertEquals(i, t.findFirstDouble(3, (double) i));
            assertEquals(i, t.findFirstFloat(4, (float) i));
            assertEquals(i, t.findFirstLong(5, i));
        }

        try {
            t.findFirstString(6, null);
            fail();
        } catch (IllegalArgumentException ignored) {}

        try {
            t.findFirstDate(2, null);
            fail();
        } catch (IllegalArgumentException ignored) {}
    }

}
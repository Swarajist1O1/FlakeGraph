class DummyClass_159587 {
    @Test
    public void getValuesFromNonExistingColumn() {
        Table t = TestHelper.getTableWithAllColumnTypes();
        t.addEmptyRows(10);

        try { t.getBinaryByteArray(-1, 0);          fail("Column is less than 0"); } catch (ArrayIndexOutOfBoundsException ignored) { }
        try { t.getBinaryByteArray(-10, 0);         fail("Column is less than 0"); } catch (ArrayIndexOutOfBoundsException ignored) { }
        try { t.getBinaryByteArray(9, 0);           fail("Column does not exist"); } catch (ArrayIndexOutOfBoundsException ignored) { }

        try { t.getBoolean(-1, 0);                  fail("Column is less than 0"); } catch (ArrayIndexOutOfBoundsException ignored) { }
        try { t.getBoolean(-10, 0);                 fail("Column is less than 0"); } catch (ArrayIndexOutOfBoundsException ignored) { }
        try { t.getBoolean(9, 0);                   fail("Column does not exist"); } catch (ArrayIndexOutOfBoundsException ignored) { }

        try { t.getDate(-1, 0);                     fail("Column is less than 0"); } catch (ArrayIndexOutOfBoundsException ignored) { }
        try { t.getDate(-10, 0);                    fail("Column is less than 0"); } catch (ArrayIndexOutOfBoundsException ignored) { }
        try { t.getDate(9, 0);                      fail("Column does not exist"); } catch (ArrayIndexOutOfBoundsException ignored) { }

        try { t.getDouble(-1, 0);                   fail("Column is less than 0"); } catch (ArrayIndexOutOfBoundsException ignored) { }
        try { t.getDouble(-10, 0);                  fail("Column is less than 0"); } catch (ArrayIndexOutOfBoundsException ignored) { }
        try { t.getDouble(9, 0);                    fail("Column does not exist"); } catch (ArrayIndexOutOfBoundsException ignored) { }

        try { t.getFloat(-1, 0);                    fail("Column is less than 0"); } catch (ArrayIndexOutOfBoundsException ignored) { }
        try { t.getFloat(-10, 0);                   fail("Column is less than 0"); } catch (ArrayIndexOutOfBoundsException ignored) { }
        try { t.getFloat(9, 0);                     fail("Column does not exist"); } catch (ArrayIndexOutOfBoundsException ignored) { }

        try { t.getLong(-1, 0);                     fail("Column is less than 0"); } catch (ArrayIndexOutOfBoundsException ignored) { }
        try { t.getLong(-10, 0);                    fail("Column is less than 0"); } catch (ArrayIndexOutOfBoundsException ignored) { }
        try { t.getLong(9, 0);                      fail("Column does not exist"); } catch (ArrayIndexOutOfBoundsException ignored) { }

        try { t.getString(-1, 0);                   fail("Column is less than 0"); } catch (ArrayIndexOutOfBoundsException ignored) { }
        try { t.getString(-10, 0);                  fail("Column is less than 0"); } catch (ArrayIndexOutOfBoundsException ignored) { }
        try { t.getString(9, 0);                    fail("Column does not exist"); } catch (ArrayIndexOutOfBoundsException ignored) { }
    }

}
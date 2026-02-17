class DummyClass_38196 {
    @Test
    public void testDeleteTable() {
        mockery.checking(new Expectations(){{
            oneOf(kvs).dropTable(with(equal(TABLE_REF)));
        }});
        Schemas.deleteTable(kvs, TABLE_REF);
    }

}
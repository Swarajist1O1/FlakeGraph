class DummyClass_91549 {
    @Test
    public void testEvalQueryMetadata() {
        ColumnDesc[] columnDescs = explorer
                .evalQueryMetadata("select cal_dt, count(*) as cnt from DEFAULT.test_kylin_fact group by cal_dt");
        Assert.assertNotNull(columnDescs);
        Assert.assertEquals(2, columnDescs.length);
        Assert.assertEquals("date", columnDescs[0].getDatatype());
        Assert.assertEquals("CAL_DT", columnDescs[0].getName());
        Assert.assertEquals("bigint", columnDescs[1].getDatatype());
        Assert.assertEquals("CNT", columnDescs[1].getName());
    }

}
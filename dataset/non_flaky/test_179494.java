class DummyClass_179494 {
    @Test
    public void testDeleteByPrimaryKey(){
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            Assert.assertEquals(183, mapper.selectAll().size());
            Assert.assertEquals(1, mapper.deleteByPrimaryKey(1L));
            Assert.assertEquals(182, mapper.selectAll().size());

            Assert.assertEquals(1, mapper.deleteByPrimaryKey(2));
            Assert.assertEquals(181, mapper.selectAll().size());

            Assert.assertEquals(1, mapper.deleteByPrimaryKey("3"));
            Assert.assertEquals(180, mapper.selectAll().size());

            Assert.assertEquals(0, mapper.deleteByPrimaryKey(1));
            Assert.assertEquals(180, mapper.selectAll().size());
        } finally {
            sqlSession.close();
        }
    }

}
class DummyClass_179479 {
    @Test(expected = PersistenceException.class)
    public void testSafeUpdateByExample() {
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            mapper.updateByExampleSelective(new Country(), new Example(Country.class));
        } finally {
            sqlSession.close();
        }
    }

}
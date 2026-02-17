class DummyClass_179481 {
    @Test(expected = PersistenceException.class)
    public void testSafeUpdate() {
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            mapper.updateByExample(new Country(), new Example(Country.class));
        } finally {
            sqlSession.close();
        }
    }

}
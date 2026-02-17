class DummyClass_179488 {
    @Test(expected = PersistenceException.class)
    public void testSafeDeleteByExample() {
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            mapper.deleteByExample(new Example(Country.class));
        } finally {
            sqlSession.close();
        }
    }

}
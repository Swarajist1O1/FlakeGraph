class DummyClass_179441 {
    @Test
    public void testCount() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            AggregateCondition aggregateCondition = AggregateCondition.builder().
                    aggregateBy("id").aliasName("total").aggregateType(AggregateType.COUNT).groupBy("role");
            Example example = new Example(User.class);
            List<User> m = mapper.selectAggregationByExample(example, aggregateCondition);
            Assert.assertEquals(2, m.size());
        } finally {
            sqlSession.close();
        }
    }

}
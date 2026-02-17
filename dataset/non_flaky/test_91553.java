class DummyClass_91553 {
    @Test
    public void testGenSqoopCmd_WithLookupShardBy() throws IOException {
        ISource source = SourceManager.getSource(new JdbcSourceAware());
        IMRInput input = source.adaptToBuildEngine(IMRInput.class);
        Assert.assertNotNull(input);

        CubeManager cubeManager = CubeManager.getInstance(getTestConfig());
        CubeDesc cubeDesc = CubeDescManager.getInstance(getTestConfig()).getCubeDesc("ut_jdbc_shard");
        CubeSegment seg = cubeManager.appendSegment(cubeManager.getCube(cubeDesc.getName()),
                new SegmentRange.TSRange(System.currentTimeMillis() - 100L, System.currentTimeMillis() + 100L));
        CubeJoinedFlatTableDesc flatDesc = new CubeJoinedFlatTableDesc(seg);
        JdbcHiveMRInput.JdbcMRBatchCubingInputSide inputSide = (JdbcHiveMRInput.JdbcMRBatchCubingInputSide) input
                .getBatchCubingInputSide(flatDesc);

        AbstractExecutable executable = new MockInputSide(flatDesc, inputSide).createSqoopToFlatHiveStep("/tmp",
                cubeDesc.getName());
        Assert.assertNotNull(executable);

        String cmd = executable.getParam("cmd");
        Assert.assertTrue(cmd.contains("org.h2.Driver"));
        Assert.assertTrue(cmd.contains(
                "--boundary-query \"SELECT MIN(\\\"TEST_CATEGORY_GROUPINGS\\\".\\\"META_CATEG_NAME\\\"), MAX(\\\"TEST_CATEGORY_GROUPINGS\\\".\\\"META_CATEG_NAME\\\")" + System.lineSeparator()
                        + "FROM \\\"DEFAULT\\\".\\\"TEST_CATEGORY_GROUPINGS\\\" AS \\\"TEST_CATEGORY_GROUPINGS\\\"\""));

        source.close();
    }

}
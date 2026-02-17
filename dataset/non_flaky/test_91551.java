class DummyClass_91551 {
    @Test
    public void testGenSqoopCmd_Partition() throws IOException {
        ISource source = SourceManager.getSource(new JdbcSourceAware());
        IMRInput input = source.adaptToBuildEngine(IMRInput.class);
        Assert.assertNotNull(input);

        CubeManager cubeManager = CubeManager.getInstance(getTestConfig());
        CubeDesc cubeDesc = CubeDescManager.getInstance(getTestConfig()).getCubeDesc("ci_inner_join_cube");
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
                "--boundary-query \"SELECT MIN(\\\"TEST_KYLIN_FACT\\\".\\\"LEAF_CATEG_ID\\\"), MAX(\\\"TEST_KYLIN_FACT\\\".\\\"LEAF_CATEG_ID\\\")" + System.lineSeparator()
                        + "FROM \\\"DEFAULT\\\".\\\"TEST_KYLIN_FACT\\\" AS \\\"TEST_KYLIN_FACT\\\""));
        source.close();
    }

}